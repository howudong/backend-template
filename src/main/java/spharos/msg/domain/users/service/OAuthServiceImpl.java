package spharos.msg.domain.users.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.in.EasySignUpRequestDto;
import spharos.msg.domain.users.dto.out.EasySignUpOutDto;
import spharos.msg.domain.users.entity.UserOAuthList;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserOAuthListRepository;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.UsersException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final UsersRepository userRepository;
    private final UserOAuthListRepository userOAuthListRepository;

    @Override
    public void easySignUp(EasySignUpRequestDto easySignUpRequestDto) {
        Users users = userRepository.findByEmail(easySignUpRequestDto.getEmail()).orElseThrow(
                () -> new UsersException(ErrorStatus.SIGN_UP_EASY_FAIL) //todo :
        );

        if(Boolean.TRUE.equals(userOAuthListRepository.existsByUuid(users.getUuid()))){
            log.info("기존회원이고 간편회원도 맞아서 로그인 처리해버림");
            //todo : 바로 간편 로그인 처리
        }
        else {
            UserOAuthList userOAuthList = UserOAuthList
                    .builder()
                    .OAuthId(easySignUpRequestDto.getOauth_id())
                    .OAuthName(easySignUpRequestDto.getOauth_name())
                    .uuid(users.getUuid())
                    .build();

            userOAuthListRepository.save(userOAuthList);
        }
    }
}
