package spharos.msg.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.cart.dto.CartProductRequestDto;
import spharos.msg.domain.cart.service.CartProductService;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartProductController {
    private final CartProductService cartProductService;
    private final UserRepository userRepository;
    @PostMapping("/option/{productOptionId}")
    public ApiResponse<?> addCart(
            @PathVariable Long productOptionId,
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        return cartProductService.addCart(productOptionId, cartProductRequestDto,userDetails.getUsername());
    }

    //장바구니 전체 조회
    //todo 유저정보 로그인 통해서 가져오기
    @GetMapping
    public ApiResponse<?> getCart(
            @AuthenticationPrincipal UserDetails userDetails

    ) {
        return cartProductService.getCart(userDetails.getUsername());
    }

    @PatchMapping("/{cartId}")
    public ApiResponse<?> updateCart(
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @PathVariable Long cartId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.updateCart(cartProductRequestDto, cartId,userDetails.getUsername());
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<?> deleteCart(
            @PathVariable Long cartId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.deleteCart(cartId, userDetails.getUsername());
    }

    //장바구니 상품 옵션 조회
    @GetMapping("/option/{productId}")
    public ApiResponse<?> getCartOption(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return cartProductService.getCartOption(productId,userDetails.getUsername());
    }
}