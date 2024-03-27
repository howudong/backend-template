package spharos.msg.domain.users.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spharos.msg.global.api.ApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "회원 관련 API")
public class UsersController {

    //todo: (이메일 인증 or 이메일 중복확인) API
    @Operation(summary = "이메일 인증", description = "이메일 인증 및 중복확인을 진행 합니다.")
    @PostMapping("") //todo:
    public ApiResponse<?> authenticateEmail() {
        return null;
    }

    //todo: 아이디 중복 확인 API
    @Operation(summary = "아이디 중복확인", description = "입력받은 아이디의 중복 여부를 확인합니다.")
    @PostMapping("") //todo: path 및 입력 어떻게 받을 것인지 확인.
    public ApiResponse<?> duplicateLoginId() {
        return null;
    }
}
