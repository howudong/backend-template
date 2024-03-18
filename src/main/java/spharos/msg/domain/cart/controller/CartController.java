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

    @PostMapping("/option/{productOptionId}")
    public ApiResponse<?> addCart(
            @PathVariable Long productOptionId,
            @RequestBody CartRequestDto cartRequestDto
    ) {
        return cartService.addCart(productOptionId,cartRequestDto);
    }

    //장바구니 전체 조회
    //todo 유저정보 로그인 통해서 가져오기
    @GetMapping("/{usersId}")
    public ApiResponse<?> getCart(
            @PathVariable Long usersId
    ) {
        return cartService.getCart(usersId);
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
    ) {
        cartService.deleteCart(cartId);
        return null;
    }

    //장바구니 상품 옵션 조회
    @GetMapping("/option/{productId}")
    public ApiResponse<?> getCartOption(
            @PathVariable Long productId
    ) {
        cartService.getCartOption(productId);

        return null;
    }
}