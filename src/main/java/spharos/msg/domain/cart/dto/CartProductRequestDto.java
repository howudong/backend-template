package spharos.msg.domain.cart.dto;

import lombok.Getter;

@Getter
public class CartProductRequestDto {
    private Long productOptionId;
    private int productQuantity;
    private boolean cartIsChecked;
}
