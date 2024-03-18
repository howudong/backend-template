package spharos.msg.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @RequestBody CartProductRequestDto cartProductRequestDto
    ) {
        return cartProductService.addCart(productOptionId, cartProductRequestDto);
    }

    //장바구니 전체 조회
    //todo 유저정보 로그인 통해서 가져오기
    @GetMapping("/{usersId}")
    public ApiResponse<?> getCart(
            @PathVariable Long usersId
    ) {
        return cartProductService.getCart(usersId);
    }

    @PatchMapping("/{cartId}")
    public ApiResponse<?> updateCart(
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @PathVariable Long cartId
    ) {
        return cartProductService.updateCart(cartProductRequestDto, cartId);
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<?> deleteCart(
            @PathVariable Long cartId
//            @AuthenticationPrincipal Users users
    ) {
        //임시 사용자
        Users users = userRepository.findById(1L).orElseThrow();
        return cartProductService.deleteCart(cartId, users.getId());
    }

    //장바구니 상품 옵션 조회
    @GetMapping("/option/{productId}")
    public ApiResponse<?> getCartOption(
            @PathVariable Long productId
    ) {
        return cartProductService.getCartOption(productId);
    }
}