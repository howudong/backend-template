package spharos.msg.domain.likes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.likes.entity.Likes;

@Getter
@NoArgsConstructor
public class LikesResponseDto {
    private Long productId;
    private String productName;
    private int productPrice;
    //별점, 댓글 수,할인율

    public LikesResponseDto(Likes likes) {
        this.productId = likes.getProduct().getId();
        this.productName = likes.getProduct().getProductName();
        this.productPrice = likes.getProduct().getProductPrice();
    }
}
