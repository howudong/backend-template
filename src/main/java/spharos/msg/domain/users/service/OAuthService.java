package spharos.msg.domain.users.service;

import spharos.msg.domain.users.dto.in.EasySignUpRequestDto;
import spharos.msg.domain.users.dto.out.EasySignUpOutDto;

public interface OAuthService {
    void easySignUp(EasySignUpRequestDto easySignUpRequestDto);
}
