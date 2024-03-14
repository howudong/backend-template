package spharos.msg.global.api.code;

import spharos.msg.global.dto.ErrorReasonDto;

public interface BaseErrorCode {
    ErrorReasonDto getReason();

    ErrorReasonDto getReasonHttpStatus();
}
