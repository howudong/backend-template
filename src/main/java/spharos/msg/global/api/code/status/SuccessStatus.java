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
    LOGIN_SUCCESS_UNION(HttpStatus.ACCEPTED, "USER204", "통합 로그인 성공"),
    LOGIN_SUCCESS_EASY(HttpStatus.ACCEPTED, "USER205", "간편 로그인 성공"),
    TOKEN_REISSUE_COMPLETE(HttpStatus.ACCEPTED, "USER206", "토큰 재발급 성공"),
    LOGOUT_SUCCESS(HttpStatus.ACCEPTED, "USER207", "로그아웃 성공"),

    CART_PRODUCT_ADD_SUCCESS(HttpStatus.CREATED,"CART301","장바구니 담기 성공"),
    CART_PRODUCT_GET_SUCCESS(HttpStatus.OK,"CART302","장바구니 조회 성공"),
    CART_PRODUCT_UPDATE_SUCCESS(HttpStatus.OK,"CART303","장바구니 수정 성공"),
    CART_PRODUCT_DELETE_SUCCESS(HttpStatus.OK,"CART304","장바구니 삭제 성공"),
    CART_PRODUCT_OPTION_SUCCESS(HttpStatus.OK,"CART305","장바구니 옵션 조회 성공");

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
