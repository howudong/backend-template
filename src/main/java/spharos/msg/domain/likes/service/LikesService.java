package spharos.msg.domain.likes.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.likes.dto.LikesResponseDto;
import spharos.msg.domain.likes.entity.Likes;
import spharos.msg.domain.likes.repository.LikesRepository;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.ApiResponse;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final ProductRepository productRepository;
    private final LikesRepository likesRepository;
    private final UsersRepository usersRepository;

    //상품에 좋아요 등록
    @Transactional
    public ApiResponse<?> likeProduct(Long productId, String userUuid) {
        //todo 로그인 안되어 있으면 로그인 페이지로 이동
        Product product = productRepository.findById(productId).orElseThrow();
        Users users = usersRepository.findByUuid(userUuid).orElseThrow();

        Likes like = Likes.builder()
                .product(product)
                .users(users)
                .isLike(true)
                .build();
        likesRepository.save(like);
        return new ApiResponse<>(true, "200", "좋아요 등록 성공", null);
    }

    @Transactional
    public ApiResponse<?> deleteLikeProduct(Long productId, String userUuid) {
        Product product = productRepository.findById(productId).orElseThrow();
        Users users = usersRepository.findByUuid(userUuid).orElseThrow();

        Likes likes = likesRepository.findByUsersAndProduct(users, product).orElseThrow();
        likesRepository.delete(likes);
        return new ApiResponse<>(true, "200", "좋아요 해제 성공", null);
    }

    @Transactional
    public ApiResponse<?> getProductLikeList(String userUuid) {
        Users users = usersRepository.findByUuid(userUuid).orElseThrow();
        return new ApiResponse<>(
                true,
                "200",
                "좋아요 상품 리스트 조회 성공",
                likesRepository.findByUsers(users)
                        .stream()
                        .map(LikesResponseDto::new)
                        .collect(Collectors.toList()));
    }
}