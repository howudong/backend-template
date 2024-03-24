package spharos.msg.domain.review.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.review.dto.ReviewRequestDto;
import spharos.msg.domain.review.service.ReviewService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-review")
@Slf4j
@Tag(name="Review",description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;
    @Operation(summary = "상품 리뷰 등록",
        description = "리뷰가 가능한 주문상세에 대해 상품 리뷰를 등록합니다")
    @PostMapping("/{productId}")
    public ApiResponse<?> saveReview(
        @PathVariable("productId") Long productId,
        @RequestBody ReviewRequestDto reviewRequestDto,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("컨트롤러호출");
    return reviewService.saveReview(productId,reviewRequestDto,userDetails.getUsername());
    }
}
