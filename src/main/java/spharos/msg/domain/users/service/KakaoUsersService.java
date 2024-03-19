package spharos.msg.domain.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.KakaoSignUpRequestDto;
import spharos.msg.domain.users.entity.KakaoUsers;
import spharos.msg.domain.users.repository.KakaoUsersRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoUsersService {
    KakaoUsersRepository kakaoUsersRepository;

    public void createKakaoUsers(KakaoSignUpRequestDto kakaoSignUpRequestDto){
        log.info("Kakao Signup complete={}", kakaoSignUpRequestDto.toString());
        kakaoUsersRepository.save(
            KakaoUsers.builder().userId(kakaoSignUpRequestDto.getUserId()).build());
    }
}
