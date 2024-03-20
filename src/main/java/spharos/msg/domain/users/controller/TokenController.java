package spharos.msg.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.users.dto.ReissueRequestDto;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.service.UsersService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reissue")
public class TokenController {

    private final UsersService usersService;

    @Operation(summary = "Security", description = "Token 재발급", tags = {"Token"})
    @PostMapping
    public ApiResponse reissueToken(
        @RequestBody ReissueRequestDto reissueRequestDto,
        @RequestHeader(name = "Authorization") String refreshToken,
        HttpServletResponse response
    ) {
        Users findUsers = usersService.CheckRefreshTokenValidation(refreshToken,
            reissueRequestDto.getUuid());

        usersService.createTokenAndCreateHeaders(response, findUsers);

        return ApiResponse.of(SuccessStatus.TOKEN_REISSUE_COMPLETE, null);
    }
}
