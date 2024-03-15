package spharos.msg.global.api.code.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spharos.msg.global.api.code.BaseCode;
import spharos.msg.global.api.dto.ReasonDto;

@Getter
@RequiredArgsConstructor
public enum SuccessStatus implements BaseCode {

    SIGN_UP_SUCCESS_UNION(HttpStatus.CREATED, "USER201", "통합 회원가입 성공"),
    SIGN_UP_SUCCESS_EASY(HttpStatus.CREATED, "USER202", "간편 회원가입 성공"),
    LOGIN_SUCCESS(HttpStatus.ACCEPTED, "USER203", "통합 로그인 성공"),
    LOGIN_SUCCESS_UNION(HttpStatus.ACCEPTED, "USER203", "통합 로그인 성공"),
    LOGIN_SUCCESS_EASY(HttpStatus.ACCEPTED, "USER203", "간편 로그인 성공");


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
