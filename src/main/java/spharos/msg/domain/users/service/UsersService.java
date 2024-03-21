package spharos.msg.domain.users.service;

import static spharos.msg.global.api.code.status.ErrorStatus.LOGIN_ID_NOT_FOUND;
import static spharos.msg.global.api.code.status.ErrorStatus.LOGIN_ID_PW_VALIDATION;
import static spharos.msg.global.api.code.status.ErrorStatus.SIGN_IN_ID_DUPLICATION;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.msg.domain.users.dto.KakaoSignUpRequestDto;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.NewAddressRequestDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.entity.Address;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.JwtTokenException;
import spharos.msg.global.api.exception.UsersException;
import spharos.msg.global.redis.RedisService;
import spharos.msg.global.security.JwtTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;
    private final KakaoUsersService kakaoUsersService;
    private final AddressService addressService;

    public static final String BEARER = "Bearer";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String BASIC_ADDRESS_NAME = "기본 배송지";

    @Value("${JWT.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${JWT.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Transactional(readOnly = true)
    public void signUpDuplicationCheck(SignUpRequestDto signUpRequestDto) {
        if (usersRepository.findByLoginId(signUpRequestDto.getLoginId()).isPresent()) {
            throw new UsersException(SIGN_IN_ID_DUPLICATION);
        }
    }

    @Transactional(readOnly = true)
    public Users login(LoginRequestDto loginRequestDto) {
        Users users = usersRepository.findByLoginId(loginRequestDto.getLoginId())
            .orElseThrow(() -> new UsersException(LOGIN_ID_NOT_FOUND));

        //비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
            throw new UsersException(LOGIN_ID_PW_VALIDATION);
        }

        //적합한 인증 provider 찾기
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                users.getUsername(),
                loginRequestDto.getPassword()
            )
        );

        return users;
    }

    @Transactional
    public Users createUsers(SignUpRequestDto signUpRequestDto) {
        Users users = Users.signUpDtoToEntity(signUpRequestDto);

        Address address = Address.NewAddressDtoToEntity(
            NewAddressRequestDto.signUpDtoToDto(signUpRequestDto, users));

        users.addAddress(address);

        usersRepository.save(users);
        addressService.createNewAddress(address);

        if (Boolean.TRUE.equals(signUpRequestDto.getIsEasy())) {
            kakaoUsersService.createKakaoUsers(
                KakaoSignUpRequestDto.uuidToDto(users.getUuid()));
        }

        return users;
    }

    //토큰 생성 후, redis에 저장
    public String createRefreshToken(Users users) {
        String token = jwtTokenProvider.generateToken(users, refreshTokenExpiration, "Refresh");
        redisService.saveRefreshToken(users.getUuid(), token, refreshTokenExpiration);
        return BEARER + "%20" + token;
    }

    public String createAccessToken(Users users) {
        return BEARER + " " + jwtTokenProvider.generateToken(users, accessTokenExpiration,
            "Access");
    }

    public void createTokenAndCreateHeaders(HttpServletResponse response, Users users) {

        //refreshToken 확인 후, 있을 시, Delete 처리
        if (Boolean.TRUE.equals(redisService.isRefreshTokenExist(users.getUuid()))) {
            log.info("Delete Token is Complete={}", redisService.getRefreshToken(users.getUuid()));
            redisService.deleteRefreshToken(users.getUuid());
        }

        String accessToken = createAccessToken(users);
        String refreshToken = createRefreshToken(users);

        doResponseCookieAndHeader(response, refreshToken, accessToken);
    }

    private void doResponseCookieAndHeader(
        HttpServletResponse response, String refreshToken, String accessToken) {
        response.addHeader(ACCESS_TOKEN, accessToken);
        Cookie cookie = new Cookie(REFRESH_TOKEN, refreshToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) refreshTokenExpiration);
        response.addCookie(cookie);
    }

    public Users checkRefreshTokenValidation(String givenRefreshToken, String uuid) {

        //Token 값 자체 유효성 검사
        if (givenRefreshToken == null || !givenRefreshToken.startsWith(BEARER + " ")) {
            throw new JwtTokenException(ErrorStatus.REISSUE_TOKEN_FAIL);
        }

        String jwt = givenRefreshToken.substring(7);

        try {
            String findRefreshToken = redisService.getRefreshToken(uuid);
            if (!findRefreshToken.matches(jwt)) {
                throw new JwtTokenException(ErrorStatus.REISSUE_TOKEN_FAIL);
            }

            return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new JwtTokenException(ErrorStatus.REISSUE_TOKEN_FAIL));
        } catch (Exception e) {
            throw new JwtTokenException(ErrorStatus.REISSUE_TOKEN_FAIL);
        }
    }

    public void userLogout(String uuid) {
        if (Boolean.TRUE.equals(redisService.isRefreshTokenExist(uuid))) {
            redisService.deleteRefreshToken(uuid);
        }
    }

    public void deleteUsers(String uuid){
        Users users = usersRepository.findByUuid(uuid).orElseThrow();
        usersRepository.deleteById(users.getId());
    }
}
