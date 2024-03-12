package spharos.msg.domain.cart.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spharos.msg.domain.cart.dto.CartRequestDto;

@Service
public class CartService {
    public void createCart(CartRequestDto cartRequestDto) {

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
