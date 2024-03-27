package spharos.msg.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.global.api.ApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
@Tag(name = "배송지", description = "배송지 관련 API")
public class AddressController {

    @Operation(summary = "배송지 추가", description = "해당 회원의 배송지를 추가 합니다.")
    @PostMapping("") //todo:
    public ApiResponse<?> authenticateEmail() {
        return null;
    }
}
