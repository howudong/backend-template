package spharos.msg.global.api.code.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spharos.msg.global.api.code.BaseErrorCode;
import spharos.msg.global.dto.ErrorReasonDto;

@Getter
@RequiredArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러 입니다"),
    INVALID_PARAMETER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON501", "서버 에러, 유효하지 않은 매개변수입니다."),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "COMMON404", "잘못된 api 주소입니다."),
    INVALID_PATH_VARIABLE(HttpStatus.BAD_REQUEST, "COMMON405", "유효하지 않은 PathVariable 입니다."),
    INVALID_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "COMMON406", "유효하지 않은 파라미터 요청입니다"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    DuplicateId(HttpStatus.BAD_REQUEST, "USER401", "통합 회원가입 실패"),
    //todo: (HttpStatus.BAD_REQUEST, "USER402", "간편 회원가입 실패"),
    EntityNotFoundId(HttpStatus.BAD_REQUEST, "USER403", "통합 로그인 실패"),

    EXAMPLE_EXCEPTION(HttpStatus.BAD_REQUEST, "EXAMPLE400", "샘플 에러 메시지입니다");

    private final HttpStatus httpStatus;
    private final String status;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .message(message)
                .status(status)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .message(message)
                .status(status)
                .httpStatus(httpStatus)
                .build();
    }
}
