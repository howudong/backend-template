package spharos.msg.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
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

    @NotNull
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Review(String reviewComment, BigDecimal reviewStar, Long userId, OrderDetail orderDetail, Product product) {
        this.reviewComment = reviewComment;
        this.reviewStar = reviewStar;
        this.userId = userId;
        this.orderDetail = orderDetail;
        this.product = product;
    }
}
