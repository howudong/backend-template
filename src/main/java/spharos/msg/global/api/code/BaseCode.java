package spharos.msg.global.api.code;

import spharos.msg.global.dto.ReasonDto;

public interface BaseCode {
    ReasonDto getReason();

    ReasonDto getReasonHttpStatus();
}
