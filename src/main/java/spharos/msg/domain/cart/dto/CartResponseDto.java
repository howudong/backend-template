package spharos.msg.domain.cart.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.cart.entity.CartProduct;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private Long productID;
    private String productName;
    private Integer productPrice;
    private boolean cartIsChecked;
    //todo 이미지, 별점 정보 가져오기
    //private String image;
    //private Integer productStar;
    private Integer productQuantity;

    public CartResponseDto(CartProduct cartProduct) {
        this.productID = cartProduct.getProductOption().getProduct().getId();
        this.productName = cartProduct.getProductOption().getProduct().getProductName();
        this.productPrice = cartProduct.getProductOption().getProduct().getProductPrice();
        this.cartIsChecked = cartProduct.getCartIsChecked();
//        this.image = cartProduct.getProductOption().getProduct().get
//        this.productStar =
        this.productQuantity = cartProduct.getCartProductQuantity();
    }
}
