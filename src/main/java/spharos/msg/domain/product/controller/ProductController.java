package spharos.msg.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.product.dto.ProductResponseDto;
import spharos.msg.domain.product.service.ProductService;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.ErrorStatus;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "홈화면 상품 조회",
    description = "홈화면 3가지 섹션(뷰티/랜덤/음식)의 상품을 조회합니다")
    @GetMapping("/product-list")
    public ApiResponse<ProductResponseDto.Home1> getHome1Products(@RequestParam("param") String state, @RequestParam("index")int index) {
        if ("HOME".equals(state) && index == 0) {
            return ApiResponse.onSuccess(productService.getHome1Products());
        }
        return ApiResponse.onFailure(ErrorStatus.PRODUCT_ERROR.getStatus(), ErrorStatus.PRODUCT_ERROR.getMessage(), null);
    }

    @Operation(summary = "홈화면 상품 조회2",
            description = "패션 카테고리 상품들을 조회합니다")
    @GetMapping("/product-list")
    public ApiResponse<ProductResponseDto.Home2> getHome2Products(@RequestParam("param") String state, @RequestParam("index")int index) {
        if ("HOME2".equals(state)) {
            return ApiResponse.onSuccess(productService.getHome2Products(index));
        }
        return ApiResponse.onFailure(ErrorStatus.PRODUCT_ERROR.getStatus(), ErrorStatus.PRODUCT_ERROR.getMessage(), null);
    }
}
