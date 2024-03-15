package spharos.msg.domain.cart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spharos.msg.domain.product.entity.ProductOption;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.global.entity.BaseEntity;

@Entity
@Getter
public class CartProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long id;

    @Column(columnDefinition = "integer default 0")
    @NotNull
    private Integer cartProductQuantity;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean cartIsChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;
}