package spharos.msg.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spharos.msg.domain.cart.dto.CartRequestDto;
import spharos.msg.domain.cart.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public void addCart(CartRequestDto cartRequestDto) {
        //상품 옵션 없는 경우 바로 add
        //
    }

    public void getCart(UserDetails userDetails) {
    }

    public void updateCart(CartRequestDto cartRequestDto, UserDetails userDetails, Long cartId) {
    }

    public void deleteCart(UserDetails userDetails, Long cartId) {
    }

    public void getCartOption(UserDetails userDetails, Long productId) {
    }
}
