package spharos.msg.domain.review.service;

import static spharos.msg.global.api.code.status.SuccessStatus.REVIEW_SAVE_SUCCESS;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.msg.domain.cart.dto.CartProductQuantityDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.review.dto.ReviewRequestDto;
import spharos.msg.domain.review.entity.Review;
import spharos.msg.domain.review.repository.ReviewRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UserRepository;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.ApiResponse;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private UsersRepository usersRepository;

    @Transactional
    public ApiResponse<?> saveReview(Long productId, ReviewRequestDto reviewRequestDto, String userUuid){
        //상품 객체 가져오기
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();
        //유저 가져오기
        Users users = usersRepository.findByUuid(userUuid).orElseThrow();
        Long userId = users.getId();
        //리뷰 객체 생성
        Review review = Review.builder()
            .product(product)
            .reviewStar(reviewRequestDto.getReviewStar())
            .reviewComment(reviewRequestDto.getReviewContent())
            .userId(userId)
            .build();
        //저장
        reviewRepository.save(review);
        return ApiResponse.of(REVIEW_SAVE_SUCCESS,null);
    };
}
