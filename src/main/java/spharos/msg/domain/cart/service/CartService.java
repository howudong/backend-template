package spharos.msg.domain.cart.service;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spharos.msg.domain.cart.dto.CartRequestDto;
import spharos.msg.domain.cart.repository.CartRepository;
import spharos.msg.global.api.ApiResponse;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public ApiResponse<?> addCart(Long productOptionId) {
        //상품 옵션 없는 경우 바로 add

        return null;
    }

    public void getCart() {
    }

    public void updateCart(CartRequestDto cartRequestDto, Long cartId) {
    }

    public void deleteCart(Long cartId) {
    }

    public void getCartOption(Long productId) {
    }

}
