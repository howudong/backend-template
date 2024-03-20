package spharos.msg.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.product.service.ProductService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.security.JwtTokenProvider;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/")
@Tag(name = "Product", description = "상품 관련 API")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "홈화면 상품 조회",
        description = "홈화면 3가지 섹션(뷰티/랜덤/음식)의 상품,패션카테고리 상품을 조회합니다")
    @GetMapping("/product-list")
    public ApiResponse<?> getHome1Products(
        @RequestParam("param") String state,
        @RequestParam("index") int index
    ) {
        log.info("홈화면 상품 조회 api 호출");
        if ("HOME".equals(state) && index == 0) {
            log.info("HOME,0으로 호출");
            return ApiResponse.onSuccess(productService.getHome1Products());
        } else if ("HOME2".equals(state)) {
            log.info("HOME,0으로 호출");
            return ApiResponse.onSuccess(productService.getHome2Products(index));
        }
        return ApiResponse.onFailure(ErrorStatus.PRODUCT_ERROR.getStatus(),
            ErrorStatus.PRODUCT_ERROR.getMessage(), null);
    }
}