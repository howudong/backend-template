package spharos.msg.domain.users.service;

import spharos.msg.domain.users.dto.in.DuplicationCheckRequestDto;
import spharos.msg.domain.users.dto.in.LoginRequestDto;
import spharos.msg.domain.users.dto.out.ReissueOutDto;
import spharos.msg.domain.users.dto.in.SignUpRequestDto;
import spharos.msg.domain.users.dto.out.LoginOutDto;

public interface AuthService {
    void signUp(SignUpRequestDto signUpRequestDto);
    LoginOutDto login(LoginRequestDto loginRequestDto);
    void logout(String uuid);
    ReissueOutDto reissueToken(String token);
    void duplicateCheckLoginId(DuplicationCheckRequestDto duplicationCheckRequestDto);
}
