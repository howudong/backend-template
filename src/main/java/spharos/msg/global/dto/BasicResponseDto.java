package spharos.msg.global.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasicResponseDto<T> {
    String statusCode;
    String message;
    T data;
}