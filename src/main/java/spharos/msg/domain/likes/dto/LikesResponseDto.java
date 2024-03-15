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
    private boolean isLike;

    public LikesResponseDto(Likes likes) {
        this.productId = likes.getProduct().getProductId();
        this.productName = likes.getProduct().getProductName();
        this.productPrice = likes.getProduct().getProductPrice();
        this.isLike = likes.isLike();
    }
}
