package spharos.msg.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.cart.dto.CartRequestDto;
import spharos.msg.domain.cart.service.CartService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ApiResponse<?> addCart(
            @RequestBody CartRequestDto cartRequestDto
    ) {
        cartService.addCart(cartRequestDto);
        return null;
    }

    //장바구니 전체 조회
    @GetMapping
    public ApiResponse<?> getCart(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        cartService.getCart(userDetails);
        return null;
    }

    @PatchMapping("/{cartId}")
    public ApiResponse<?> updateCart(
            @RequestBody CartRequestDto cartRequestDto,
            @PathVariable Long cartId
    ) {
        return null;
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<?> deleteCart(
            @PathVariable Long cartId
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long cartId
    ) {
        cartService.deleteCart(userDetails, cartId);
        return null;
    }

    //장바구니 상품 옵션 조회
    @GetMapping("/option/{productId}")
    public ApiResponse<?> getCartOption(
            @PathVariable Long productId 
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId
    ) {
        cartService.getCartOption(userDetails, productId);

        return null;
    }
}