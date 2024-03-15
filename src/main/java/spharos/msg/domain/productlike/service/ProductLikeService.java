package spharos.msg.domain.productlike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.productlike.dto.ProductLikeResponseDto;
import spharos.msg.domain.productlike.entity.ProductLike;
import spharos.msg.domain.productlike.repository.ProductLikeRepository;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;
import spharos.msg.global.api.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductLikeService {
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final UserRepository userRepository;

    //상품에 좋아요 등록
    @Transactional
    public ApiResponse<?> likeProduct(Long productId, Long userId) {
        //todo 로그인 안되어 있으면 로그인 페이지로 이동
        Product product = productRepository.findById(productId).orElseThrow();
        Users users = userRepository.findById(userId).orElseThrow();

        ProductLike like = ProductLike.builder()
                .product(product)
                .users(users)
                .isLike(true)
                .build();
        productLikeRepository.save(like);
        return new ApiResponse<>("200", "좋아요 등록 성공", null);
    }

    @Transactional
    public ApiResponse<?> deleteLikeProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow();
        Users users = userRepository.findById(userId).orElseThrow();

        ProductLike productLike = productLikeRepository.findByUsersAndProduct(users, product).orElseThrow();
        productLikeRepository.delete(productLike);
        return new ApiResponse<>("200", "좋아요 해제 성공", null);
    }

    @Transactional
    public ApiResponse<?> getProductLikeList(Long usersId) {
        Users users = userRepository.findById(usersId).orElseThrow();
        return new ApiResponse<>(
                "200",
                "좋아요 상품 리스트 조회 성공",
                productLikeRepository.findByUsers(users)
                        .stream()
                        .map(ProductLikeResponseDto::new)
                        .collect(Collectors.toList()));
    }
}