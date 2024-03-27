package spharos.msg.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.users.dto.in.LoginRequestDto;
import spharos.msg.domain.users.dto.in.SignUpRequestDto;
import spharos.msg.domain.users.service.AuthService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Auth")
@Tag(name = "Auth", description = "Auth 관련 API")
public class AuthController {

    private final AuthService authService;

    //회원가입
    @Operation(summary = "통합회원가입", description = "통합회원 회원가입")
    @PostMapping("/signup/union")
    public ApiResponse<?> signUpUnion(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
        return ApiResponse.of(SuccessStatus.SIGN_UP_SUCCESS_UNION, null);
    }

    //로그인
    @Operation(summary = "로그인", description = "통합회원 로그인")
    @PostMapping("/login/union")
    public ApiResponse<?> loginUnion(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        //todo : service 추가
        return ApiResponse.of(SuccessStatus.LOGIN_SUCCESS_UNION, null);
    }

    //로그아웃
    @Operation(summary = "로그아웃", description = "로그인 회원 로그아웃")
    @PatchMapping("/logout")
    public ApiResponse<?> logout(@RequestBody String uuid) {
        //todo : service 추가
        return ApiResponse.of(SuccessStatus.LOGOUT_SUCCESS, null);
    }

    //토큰 재발급
    @Operation(summary = "Reissue Token", description = "Access Token 재발급")
    @PostMapping
    public ApiResponse<?> reissueToken(
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response
    ) {
        //todo : service 추가
        return ApiResponse.of(SuccessStatus.TOKEN_REISSUE_COMPLETE, null);
    }
    //
}
