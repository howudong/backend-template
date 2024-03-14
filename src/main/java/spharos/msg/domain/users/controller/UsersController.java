package spharos.msg.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.LoginResponseDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.dto.SignUpResponseDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.service.UsersService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class  UsersController {
    private final UsersService usersService;

    @Operation(summary = "통합회원가입", description = "통합회원가입", tags = { "User Signup" })
    @PostMapping("signup/union")
    public ApiResponse<?> signUpUnion(@RequestBody SignUpRequestDto signUpRequestDto){
        usersService.signUp(signUpRequestDto);
        return ApiResponse.of(SuccessStatus.SIGN_UP_SUCCESS_UNION, null);
    }

    @Operation(summary = "간편회원가입", description = "간편회원가입", tags = { "User Signup" })
    @PostMapping("signup/easy")
    public ApiResponse<SignUpResponseDto> signUpEasy(@RequestBody SignUpRequestDto signUpRequestDto){
        //todo : signup 구현
        //usersService.createAccessToken()
        return null;
    }

    @Operation(summary = "로그인", description = "통합회원 로그인", tags = { "User Login" })
    @PostMapping("/login/union")
    public ApiResponse<LoginResponseDto> loginUnion(@RequestBody LoginRequestDto loginRequestDto){

        //todo : service.login 구현
        Users loginUsers = usersService.login(loginRequestDto);
        return null;
    }
}
