package spharos.msg.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @Schema(description = "상품 id")
    private Long productId;
    @Schema(description = "주문 상세 id")
    private Long orderDetailId;
    @Schema(description = "리뷰 별점")
    private BigDecimal reviewStar;
    @Schema(description = "리뷰 내용")
    private String reviewContent;
    @Builder
    private ReviewRequestDto(Long productId, Long orderDetailId, BigDecimal reviewStar, String reviewContent){
        this.productId = productId;
        this.orderDetailId = orderDetailId;
        this.reviewStar = reviewStar;
        this.reviewContent = reviewContent;
    }
}
