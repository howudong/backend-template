package spharos.msg.global.api.code.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spharos.msg.global.api.code.BaseCode;
import spharos.msg.global.api.dto.ReasonDto;

@Getter
@RequiredArgsConstructor
public enum SuccessStatus implements BaseCode {
    LOGIN_SUCCESS(HttpStatus.ACCEPTED, "USER202", "로그인 성공"),

    SIGN_UP_SUCCESS(HttpStatus.CREATED, "USER201", "회원가입 성공");

    private final HttpStatus httpStatus;
    private final String status;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .message(message)
                .status(status)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .message(message)
                .status(status)
                .httpStatus(httpStatus)
                .build();

    }
}
