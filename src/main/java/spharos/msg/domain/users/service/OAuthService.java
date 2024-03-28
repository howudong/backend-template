package spharos.msg.domain.users.service;

import spharos.msg.domain.users.dto.in.EasyLoginRequestDto;
import spharos.msg.domain.users.dto.in.EasySignUpRequestDto;
import spharos.msg.domain.users.dto.out.LoginOutDto;

public interface OAuthService {

    LoginOutDto easySignUp(EasySignUpRequestDto easySignUpRequestDto);

    LoginOutDto easyLogin(EasyLoginRequestDto easyLoginRequestDto);
}
