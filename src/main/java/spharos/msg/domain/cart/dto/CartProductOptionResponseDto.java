package spharos.msg.domain.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.product.entity.ProductOption;

@Getter
@NoArgsConstructor
public class CartProductOptionResponseDto {
    private Long productOptionId;
    private Integer productStock;
    private String optionColor;
    private String optionSize;
    private String optionEtc;

    public CartProductOptionResponseDto(ProductOption productOption){
        this.productOptionId = productOption.getProductOptionId();
        this.productStock = productOption.getStock();
        if (productOption.getOptionColor() != null) {
            this.optionColor = productOption.getOptionColor().getProductColor();
        } else {
            this.optionColor = null;
        }
        if (productOption.getOptionColor() != null) {
            this.optionSize = productOption.getOptionSize().getProductSize();
        } else {
            this.optionSize = null;
        }
        if (productOption.getOptionColor() != null) {
            this.optionEtc = productOption.getOptionEtc().getProductEtc();
        } else {
            this.optionEtc = null;
        }
    }
}