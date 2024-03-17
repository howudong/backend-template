package spharos.msg.domain.users.service;

import static spharos.msg.global.api.code.status.ErrorStatus.LOGIN_ID_NOT_FOUND;
import static spharos.msg.global.api.code.status.ErrorStatus.LOGIN_ID_PW_VALIDATION;
import static spharos.msg.global.api.code.status.ErrorStatus.SIGN_IN_ID_DUPLICATION;

import java.util.UUID;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.exception.LoginIdNotFoundException;
import spharos.msg.global.api.exception.LoginPwValidationException;
import spharos.msg.global.api.exception.SignUpDuplicationException;
import spharos.msg.global.security.JwtTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    final String BEARER = "Bearer";
    final String ACCESS_TOKEN = "accessToken";
    final String REFRESH_TOKEN = "refreshToken";

    @Value("${JWT.access-token-expiration}")
    private long access_token_expiration;
    @Value("${JWT.refresh-token-expiration}")
    private long refresh_token_expiration;

    @Transactional(readOnly = false)
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (usersRepository.findByLoginId(signUpRequestDto.getLoginId()).isPresent()) {
            throw new SignUpDuplicationException(SIGN_IN_ID_DUPLICATION);
        }
        createUsers(signUpRequestDto);
    }

    @Transactional(readOnly = false)
    public Users login(LoginRequestDto loginRequestDto) {
        log.info("try login id={}", loginRequestDto.getLoginId());
        Users users = usersRepository.findByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(() -> new LoginIdNotFoundException(LOGIN_ID_NOT_FOUND));

        //비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
            throw new LoginPwValidationException(LOGIN_ID_PW_VALIDATION);
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

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void createUsers(SignUpRequestDto signUpRequestDto) {

        UUID uuid = UUID.randomUUID();
        Users users = Users.builder()
                .loginId(signUpRequestDto.getLoginId())
                .password(signUpRequestDto.getPassword())
                .userName(signUpRequestDto.getUsername())
                .email(signUpRequestDto.getEmail())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .uuid(uuid.toString())
                .build();

        //todo : address 배송지 Entity에 추가 필요.
        //todo : 카카오 사용자 확인 필요? 간편회원가입용 createUsers 메서드 나눌것인지 확인도 필요.

        users.hashPassword(signUpRequestDto.getPassword());

        usersRepository.save(users);
    }

    public String createRefreshToken(Users users) {
        return BEARER + "%20" + jwtTokenProvider.generateToken(users, refresh_token_expiration, "Refresh");
    }

    public String createAccessToken(Users users) {
        return BEARER + " " + jwtTokenProvider.generateToken(users, access_token_expiration, "Access");
    }

    public void createTokenAndCreateHeaders(HttpServletResponse response, Users users) {
        String accessToken = createAccessToken(users);
        String refreshToken = createRefreshToken(users);

        response.addHeader(ACCESS_TOKEN, accessToken);

        Cookie cookie = new Cookie(REFRESH_TOKEN, refreshToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) refresh_token_expiration);
        response.addCookie(cookie);
    }
}
