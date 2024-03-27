package spharos.msg.domain.users.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.in.LoginRequestDto;
import spharos.msg.domain.users.dto.out.LoginOutDto;
import spharos.msg.domain.users.dto.out.SignUpOutDto;
import spharos.msg.domain.users.dto.in.SignUpRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.UsersException;
import spharos.msg.global.redis.RedisService;
import spharos.msg.global.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public static final String BEARER = "Bearer";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
    @Value("${JWT.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${JWT.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public SignUpOutDto signUp(SignUpRequestDto signUpRequestDto) {
        String uuid = UUID.randomUUID().toString();
        Users user = new Users(uuid);
        user.passwordToHash(signUpRequestDto.getPassword());

        usersRepository.save(Users
                .builder()
                .email(signUpRequestDto.getEmail())
                .userName(signUpRequestDto.getUsername())
                .loginId(signUpRequestDto.getLoginId())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .password(user.getPassword())
                .uuid(user.getUuid())
                .address(signUpRequestDto.getAddress())
                .build());

        //모델매퍼 사용해서 Dto로 변경
        return null;
    }

    @Override
    public LoginOutDto login(LoginRequestDto loginRequestDto) {
        Users findUser = usersRepository.findByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(() -> new UsersException(ErrorStatus.LOG_IN_UNION_FAIL));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        findUser.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        //create token
        String accessToken = createAccessToken(findUser);
        String refreshToken = createRefreshToken(findUser);

        return LoginOutDto
                .builder()
                .uuid(findUser.getUuid())
                .accessToken(refreshToken)
                .accessToken(accessToken)
                .name(findUser.getUsername())
                .email(findUser.getEmail())
                .build();
    }

    private String createRefreshToken(Users users) {
        String token = jwtTokenProvider.generateToken(users, refreshTokenExpiration, REFRESH_TOKEN);
        String uuid = users.getUuid();

        if(Boolean.TRUE.equals(redisService.isRefreshTokenExist(uuid))){
            redisService.deleteRefreshToken(uuid);
        }

        redisService.saveRefreshToken(uuid, token, refreshTokenExpiration);
        return BEARER + "%20" + token;
    }

    private String createAccessToken(Users users) {
        String token = jwtTokenProvider.generateToken(users, accessTokenExpiration, ACCESS_TOKEN);
        return BEARER + " " + token;
    }

    public void logout(String uuid) {
        if (Boolean.TRUE.equals(redisService.isRefreshTokenExist(uuid))) {
            redisService.deleteRefreshToken(uuid);
        }
    }
}
