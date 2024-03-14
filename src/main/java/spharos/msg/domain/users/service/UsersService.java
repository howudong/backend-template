package spharos.msg.domain.users.service;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.security.JwtTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Value("${JWT.access-token-expiration}")
    private long access_token_expiration;
    @Value("${JWT.refresh-token-expiration}")
    private long refresh_token_expiration;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if(usersRepository.findByLoginId(signUpRequestDto.getLogin_id()).isPresent()){
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        Users users = createUsers(signUpRequestDto);
    }

    public Users login(LoginRequestDto loginRequestDto) {
        Users users = usersRepository.findByLoginId(loginRequestDto.getLogin_id())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다"));

        //비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
            throw new IllegalArgumentException("사용자 아이디와 패스워드가 매칭되지 않습니다.");
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

    private Users createUsers(SignUpRequestDto signUpRequestDto) {

        UUID uuid = UUID.randomUUID();
        Users users = Users.builder()
            .loginId(signUpRequestDto.getLogin_id())
            .password(signUpRequestDto.getPassword())
            .userName(signUpRequestDto.getUsername())
            .email(signUpRequestDto.getEmail())
            .phoneNumber(signUpRequestDto.getPhone_number())
            .uuid(uuid.toString())
            .build();

        //todo : address 배송지 Entity에 추가 필요.
        //

        users.hashPassword(signUpRequestDto.getPassword());
        log.info("users: {}", users.toString());
        return usersRepository.save(users);
    }

    public String createRefreshToken(Users users) {
        return jwtTokenProvider.generateToken(users, refresh_token_expiration);

    }
    public String createAccessToken(Users users) {
        return jwtTokenProvider.generateToken(users, access_token_expiration);
    }
}
