package spharos.msg.global.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spharos.msg.global.api.code.BaseErrorCode;
import spharos.msg.global.api.dto.ErrorReasonDto;

@Getter
@RequiredArgsConstructor
public class JwtTokenValidationException extends RuntimeException {
    //todo : Exception common 처리. handler 에서 getErrorReason 활용하여 message 만 바꾸는 형식으로 처리.

    private final BaseErrorCode errorCode;

    public ErrorReasonDto getErrorReason() {
        return errorCode.getReason();
    }

    public ErrorReasonDto getErrorReasonHttpStatus() {
        return errorCode.getReasonHttpStatus();
    }
}
