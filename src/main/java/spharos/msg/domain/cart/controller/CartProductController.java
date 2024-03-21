package spharos.msg.domain.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.cart.dto.CartProductRequestDto;
import spharos.msg.domain.cart.service.CartProductService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "CartProduct", description = "장바구니 API")
public class CartProductController {
    private final CartProductService cartProductService;

    @Operation(summary = "장바구니 담기",
            description = "옵션에 해당되는 상품을 장바구니에 추가합니다.")
    @PostMapping("/option/{productOptionId}")
    public ApiResponse<?> addCart(
            @PathVariable Long productOptionId,
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.addCart(productOptionId, cartProductRequestDto, userDetails.getUsername());
    }

    @Operation(summary = "장바구니 조회",
            description = "장바구니에 담긴 상품들을 조회합니다.")
    @GetMapping
    public ApiResponse<?> getCart(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.getCart(userDetails.getUsername());
    }

    @Operation(summary = "장바구니 수정",
            description = "장바구니에 담긴 상품 옵션/수량을 수정합니다.")
    @PatchMapping("/{cartId}")
    public ApiResponse<?> updateCart(
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @PathVariable Long cartId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.updateCart(cartProductRequestDto, cartId, userDetails.getUsername());
    }

    @Operation(summary = "장바구니 삭제",
            description = "장바구니에 담긴 상품을 삭제합니다.")
    @DeleteMapping("/{cartId}")
    public ApiResponse<?> deleteCart(
            @PathVariable Long cartId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.deleteCart(cartId, userDetails.getUsername());
    }

    @Operation(summary = "장바구니 상품 옵션 조회",
            description = "장바구니에 담긴 상품의 옵션을 조회합니다.")
    @GetMapping("/option/{productId}")
    public ApiResponse<?> getCartOption(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.getCartOption(productId, userDetails.getUsername());
    }
}