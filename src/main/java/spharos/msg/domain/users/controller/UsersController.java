package spharos.msg.domain.users.controller;

import static spharos.msg.global.api.code.status.SuccessStatus.LOGIN_SUCCESS_UNION;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.LoginResponseDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.service.UsersService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "통합회원가입", description = "통합회원가입", tags = {"User Signup"})
    @PostMapping("signup/union")
    public ApiResponse<?> signUpUnion(@RequestBody SignUpRequestDto signUpRequestDto) {
        usersService.signUp(signUpRequestDto);
        return ApiResponse.of(SuccessStatus.SIGN_UP_SUCCESS_UNION, null);
    }

    @Operation(summary = "간편회원가입", description = "간편회원가입", tags = {"User Signup"})
    @PostMapping("signup/easy")
    public ApiResponse<?> signUpEasy(@RequestBody SignUpRequestDto signUpRequestDto) {
        //todo : signup 구현
        return null;
    }

    @Operation(summary = "로그인", description = "통합회원 로그인", tags = {"User Login"})
    @PostMapping("/login/union")
    public ResponseEntity<LoginResponseDto> loginUnion(
        @RequestBody LoginRequestDto loginRequestDto) {
        Users loginUsers = usersService.login(loginRequestDto);

        String accessToken = "Bearer " + usersService.createAccessToken(loginUsers);
        String refreshToken = "Bearer " + usersService.createRefreshToken(loginUsers);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", accessToken);
        headers.add(HttpHeaders.SET_COOKIE,
            cookie.getName() + "=" + cookie.getValue() + "; Secure; HttpOnly");

        return ResponseEntity
            .status(HttpStatus.OK)
            .headers(headers)
            .body(LoginResponseDto
                .builder()
                .status(LOGIN_SUCCESS_UNION.getStatus())
                .message(LOGIN_SUCCESS_UNION.getMessage())
                .build());
    }

    @Operation(summary = "로그인", description = "간편회원 로그인", tags = {"User Login"})
    @PostMapping("/login/easy")
    public ResponseEntity<LoginResponseDto> loginEasy(
        @RequestBody LoginRequestDto loginRequestDto) {
        //todo :
        return null;
    }
}
