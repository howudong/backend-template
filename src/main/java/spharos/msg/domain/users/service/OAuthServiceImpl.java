package spharos.msg.domain.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.in.EasyLoginRequestDto;
import spharos.msg.domain.users.dto.in.EasySignUpRequestDto;
import spharos.msg.domain.users.dto.out.LoginOutDto;
import spharos.msg.domain.users.entity.UserOAuthList;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserOAuthListRepository;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.UsersException;
import spharos.msg.global.security.JwtTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final UsersRepository userRepository;
    private final UserOAuthListRepository userOAuthListRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void easySignUp(EasySignUpRequestDto easySignUpRequestDto) {
        Users users = userRepository.findByEmail(easySignUpRequestDto.getEmail()).orElseThrow(
                () -> new UsersException(ErrorStatus.NOT_UNION_USER)
        );

        if (Boolean.TRUE.equals(userOAuthListRepository.existsByUuid(users.getUuid()))) {
            log.info("기존회원이고 간편회원도 맞아서 로그인 처리해버림");
            //todo : 바로 간편 로그인 처리
        } else {
            UserOAuthList userOAuthList = UserOAuthList
                    .builder()
                    .OAuthId(easySignUpRequestDto.getOauth_id())
                    .OAuthName(easySignUpRequestDto.getOauth_name())
                    .uuid(users.getUuid())
                    .build();

            userOAuthListRepository.save(userOAuthList);
        }
    }

    @Override
    public LoginOutDto easyLogin(EasyLoginRequestDto easyLoginRequestDto) {
        UserOAuthList userOAuthList = userOAuthListRepository.findByOAuthIdAndOAuthName(
                easyLoginRequestDto.getOAuthId(),
                easyLoginRequestDto.getOAuthName()).orElseThrow(
                () -> new UsersException(ErrorStatus.LOG_IN_EASY_FAIL));

        Users findUser = userRepository.findByUuid(userOAuthList.getUuid()).orElseThrow(
                () -> new UsersException(ErrorStatus.NOT_UNION_USER));

        //todo : 인증처리 생각 할 것.
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        findUser.getUsername(),
//                        loginRequestDto.getPassword()
//                )
//        );

        //토큰생성
        String refreshToken = jwtTokenProvider.createRefreshToken(findUser);
        String accessToken = jwtTokenProvider.createAccessToken(findUser);

        return LoginOutDto
                .builder()
                .uuid(findUser.getUuid())
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .name(findUser.getUsername())
                .email(findUser.getEmail())
                .build();
    }
}
