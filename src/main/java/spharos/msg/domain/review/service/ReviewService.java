package spharos.msg.domain.review.service;

import static org.hibernate.query.sqm.tree.SqmNode.log;
import static spharos.msg.global.api.code.status.ErrorStatus.REVIEW_SAVE_FAIL;
import static spharos.msg.global.api.code.status.SuccessStatus.REVIEW_SAVE_SUCCESS;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;
import spharos.msg.domain.review.dto.ReviewRequestDto;
import spharos.msg.domain.review.entity.Review;
import spharos.msg.domain.review.repository.ReviewRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.ApiResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private UsersRepository usersRepository;

    @Transactional
    public ApiResponse<?> saveReview(Long productId, ReviewRequestDto reviewRequestDto, String userUuid){
        log.info("서비스호출");
        try {//상품 객체 가져오기
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
            return ApiResponse.of(REVIEW_SAVE_SUCCESS, null);
        } catch (Exception e) {
            return ApiResponse.onFailure(REVIEW_SAVE_FAIL, null);
        }
    };
}
