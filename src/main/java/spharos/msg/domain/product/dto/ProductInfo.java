package spharos.msg.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spharos.msg.domain.product.entity.Product;

@Getter
@Setter
public class ProductInfo {
    @Schema(description = "상품 id")
    private Integer productId;
    @Schema(description = "상품 이름")
    private String productName;
    @Schema(description = "상품 가격")
    private Integer productPrice;
    @Schema(description = "좋아요 여부")
    private boolean isLike;
    @Schema(description = "상품 이미지")
    private String image;
    @Schema(description = "상품 별점")
    private Short productStar;
    @Schema(description = "할인율")
    private float discountRate;

    @Builder
    private ProductInfo(Integer productId, String productName, Integer productPrice, boolean isLike, String image, Short productStar, float discountRate) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isLike = isLike;
        this.image = image;
        this.productStar = productStar;
        this.discountRate = discountRate;
    }
}
