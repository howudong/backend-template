package spharos.msg.domain.users.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.users.dto.KakaoLoginRequestDto;
import spharos.msg.domain.users.dto.LoginRequestDto;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.service.UsersService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "회원 관련 API")
public class UsersController {

    private final UsersService usersService;

    //todo: (이메일 인증 or 이메일 중복확인) API
    //todo: 아이디 중복 확인 API

    @Operation(summary = "통합회원가입", description = "통합회원 회원가입")
    @PostMapping("/signup/union")
    public ApiResponse<?> signUpUnion(@RequestBody SignUpRequestDto signUpRequestDto) {
        usersService.createUsers(signUpRequestDto);
        return ApiResponse.of(SuccessStatus.SIGN_UP_SUCCESS_UNION, null);
    }

    @Operation(summary = "간편회원가입", description = "간편회원 회원가입")
    @PostMapping("/signup/easy")
    public ApiResponse<?> signUpEasy(@RequestBody SignUpRequestDto signUpRequestDto) {
        signUpRequestDto.setIsEasy(true);
        usersService.createEasyAndUnionUsers(signUpRequestDto);
        return ApiResponse.of(SuccessStatus.SIGN_UP_SUCCESS_EASY, null);
    }

    @Operation(summary = "로그인", description = "통합회원 로그인")
    @PostMapping("login/union")
    public ApiResponse<?> loginUnion(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response
    ) {
        Users loginUsers = usersService.login(loginRequestDto);
        usersService.createTokenAndCreateHeaders(response, loginUsers);
        return ApiResponse.of(SuccessStatus.LOGIN_SUCCESS_UNION, null);
    }

    @Operation(summary = "로그인", description = "간편회원 로그인")
    @PostMapping("/login/easy")
    public ApiResponse<?> loginEasy(
            @RequestBody KakaoLoginRequestDto kakaoLoginRequestDto,
            HttpServletResponse response) {
        Users loginUsers = usersService.easyLogin(kakaoLoginRequestDto);
        usersService.createTokenAndCreateHeaders(response, loginUsers);
        return ApiResponse.of(SuccessStatus.LOGIN_SUCCESS_EASY, null);
    }

    @Operation(summary = "로그아웃", description = "로그인 회원 로그아웃")
    @PatchMapping("/logout")
    public ApiResponse<?> logout(@RequestBody String uuid) {
        usersService.userLogout(uuid);
        return ApiResponse.of(SuccessStatus.LOGOUT_SUCCESS, null);
    }

    @Operation(summary = "Reissue Token", description = "Access Token 재발급")
    @PostMapping
    public ApiResponse<?> reissueToken(
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response
    ) {
        //Refresh 인지 Access 인지 확인 로직 필요?
        Users findUsers = usersService.getUsersByUuid(userDetails.getUsername());
        usersService.createTokenAndCreateHeaders(response, findUsers);

        return ApiResponse.of(SuccessStatus.TOKEN_REISSUE_COMPLETE, null);
    }
}
