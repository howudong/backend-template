package spharos.msg.domain.cart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spharos.msg.domain.cart.dto.CartProductRequestDto;
import spharos.msg.domain.cart.dto.CartProductResponseDto;
import spharos.msg.domain.cart.entity.CartProduct;
import spharos.msg.domain.cart.repository.CartProductRepository;
import spharos.msg.domain.product.entity.ProductOption;
import spharos.msg.domain.product.repository.ProductOptionRepository;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final UserRepository userRepository;

    @Transactional
    public ApiResponse<?> addCart(Long productOptionId, CartProductRequestDto cartProductRequestDto) {
        //todo 상품 옵션 없는 경우 바로 add
        ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품옵션입니다.")
        );
        Users users = userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        CartProduct cartProduct = CartProduct.builder()
                .cartProductQuantity(cartProductRequestDto.getProductQuantity())
                .cartIsChecked(false)
                .users(users)
                .productOption(productOption)
                .build();

        cartProductRepository.save(cartProduct);

        return ApiResponse.of(SuccessStatus.CART_PRODUCT_ADD_SUCCESS, null);
    }

    @Transactional
    public ApiResponse<?> getCart(Long usersId) {
        Users users = userRepository.findById(usersId).orElseThrow();

        return ApiResponse.of(SuccessStatus.CART_PRODUCT_GET_SUCCESS,
                cartProductRepository.findByUsers(users)
                        .stream()
                        .map(CartProductResponseDto::new)
                        .collect(Collectors.toList()));
    }

    @Transactional
    public ApiResponse<?> updateCart(CartProductRequestDto cartProductRequestDto, Long cartId) {
        CartProduct cartProduct = cartProductRepository.findById(cartId).orElseThrow();
        ProductOption productOption = productOptionRepository.findById(cartProductRequestDto.getProductOptionId()).orElseThrow();
        cartProduct.updateCartProduct(cartProductRequestDto, productOption);

        return ApiResponse.of(SuccessStatus.CART_PRODUCT_UPDATE_SUCCESS,
                new CartProductResponseDto(cartProduct));
    }

    @Transactional
    public ApiResponse<?> deleteCart(Long cartId, Long usersId) {
        CartProduct cartProduct = cartProductRepository.findById(cartId).orElseThrow();
        Users users = userRepository.findById(usersId).orElseThrow();
        cartProductRepository.delete(cartProduct);

        return ApiResponse.of(SuccessStatus.CART_PRODUCT_DELETE_SUCCESS, null);
    }

    @Transactional
    public void getCartOption(Long productId) {
    }

}
