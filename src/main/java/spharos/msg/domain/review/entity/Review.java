package spharos.msg.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import spharos.msg.domain.orders.entity.OrderDetail;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.global.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 500)
    private String reviewComment;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal reviewStar;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users users;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
