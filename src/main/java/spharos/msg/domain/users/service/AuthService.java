package spharos.msg.domain.users.service;

import spharos.msg.domain.users.dto.in.LoginRequestDto;
import spharos.msg.domain.users.dto.out.SignUpOutDto;
import spharos.msg.domain.users.dto.in.SignUpRequestDto;
import spharos.msg.domain.users.dto.out.LoginOutDto;

public interface AuthService {
    SignUpOutDto signUp(SignUpRequestDto signUpRequestDto);
    LoginOutDto login(LoginRequestDto loginRequestDto);
    void logout(String uuid);
}
