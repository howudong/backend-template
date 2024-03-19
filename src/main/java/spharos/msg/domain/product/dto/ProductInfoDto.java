package spharos.msg.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInfoDto {

    @Schema(description = "상품 id")
    private Long productId;
    @Schema(description = "상품 이름")
    private String productName;
    @Schema(description = "상품 정상가")
    private Integer productPrice;
    @Schema(description = "좋아요 여부")
    @JsonProperty("isLike")
    private boolean isLike;
    @Schema(description = "장바구니 여부")
    @JsonProperty("isCart")
    private boolean isCart;
    @Schema(description = "상품 이미지")
    private String image;
    @Schema(description = "상품 별점")
    private Short productStar;
    @Schema(description = "상품 할인율")
    private BigDecimal discountRate;
    @Schema(description = "상품 할인가")
    private Integer discountPrice;

    @Builder
    private ProductInfoDto(Long productId, String productName, Integer productPrice, boolean isLike,
        boolean isCart, String image, Short productStar, BigDecimal discountRate, Integer discountPrice) {

        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isLike = isLike;
        this.isCart = isCart;
        this.image = image;
        this.productStar = productStar;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
    }
}
