package spharos.msg.domain.productlike.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.productlike.entity.ProductLike;

@Getter
@NoArgsConstructor
public class ProductLikeResponseDto {
    private Long productId;
    private String productName;
    private int productPrice;

    public ProductLikeResponseDto(ProductLike productLike) {
        this.productId = productLike.getProduct().getProductId();
        this.productName = productLike.getProduct().getProductName();
        this.productPrice = productLike.getProduct().getProductPrice();
    }
}
