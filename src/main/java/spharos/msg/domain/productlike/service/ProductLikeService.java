package spharos.msg.domain.productlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.productlike.entity.ProductLike;
import spharos.msg.domain.productlike.repository.ProductLikeRepository;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProductLikeService {
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
private final UserRepository userRepository;
    //상품에 좋아요 등록
    public void likeProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
        Users users = userRepository.findById(userId).orElseThrow();

        ProductLike like = ProductLike.builder()
                .product(product)
                .user(users)
                .isLike(true)
                .build();

        productLikeRepository.save(like);
    }
    public void deleteLikeProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
        Users users = userRepository.findById(userId).orElseThrow();

    }
}
