package spharos.msg.domain.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Integer cartProductQuantity;
    private boolean isChecked;
    private boolean cartIsCheck;
}
