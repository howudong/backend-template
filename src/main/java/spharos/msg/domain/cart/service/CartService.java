package spharos.msg.domain.cart.service;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spharos.msg.domain.cart.dto.CartRequestDto;
import spharos.msg.domain.cart.entity.CartProduct;
import spharos.msg.domain.cart.repository.CartRepository;
import spharos.msg.domain.product.entity.ProductOption;
import spharos.msg.domain.product.repository.ProductOptionRepository;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.BaseErrorCode;
import spharos.msg.global.api.code.status.SuccessStatus;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final UserRepository userRepository;

    public ApiResponse<?> addCart(Long productOptionId, CartRequestDto cartRequestDto) {
        //todo 상품 옵션 없는 경우 바로 add
        ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 상품옵션입니다.")
        );
        Users users = userRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        CartProduct cartProduct = CartProduct.builder()
                .cartProductQuantity(cartRequestDto.getProductQuantity())
                .cartIsChecked(false)
                .users(users)
                .productOption(productOption)
                .build();

        cartRepository.save(cartProduct);

        return ApiResponse.of(SuccessStatus.CART_PRODUCT_ADD_SUCCESS, null);
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
