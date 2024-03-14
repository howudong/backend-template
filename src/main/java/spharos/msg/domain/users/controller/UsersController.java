package spharos.msg.domain.users.controller;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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

@Slf4j
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
        return null;
    }

    @Operation(summary = "로그인", description = "통합회원 로그인", tags = { "User Login" })
    @PostMapping("/login/union")
    public ResponseEntity<LoginResponseDto> loginUnion(@RequestBody LoginRequestDto loginRequestDto){

        //todo : service.login 구현
        Users loginUsers = usersService.login(loginRequestDto);

        String accessToken = "Bearer " + usersService.createAccessToken(loginUsers);
        String refreshToken = "Bearer " + usersService.createRefreshToken(loginUsers);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
            .status("USER203").message("통합 로그인 성공").build();
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshToken)
            .httpOnly(true).secure(true).path("/").build();

        return ResponseEntity.ok()
            .header(SET_COOKIE, responseCookie.toString())
            .header("accessToken", accessToken)
            .body(loginResponseDto);
    }
}
