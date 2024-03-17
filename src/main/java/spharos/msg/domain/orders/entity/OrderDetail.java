package spharos.msg.domain.orders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spharos.msg.global.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
public class OrderDetail extends BaseEntity {
    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(columnDefinition = "bigint default 0")
    private Long orderProductQuantity;

    @Column
    private String orderProductColor;
    @Column
    private Long orderProductSize;
    @Column
    private String getOrderEtc;
    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean orderIsCompleted;
    @NotNull
    private Long orderPrice;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    @Column(columnDefinition = "decimal default 0.0")
    private BigDecimal discountRatio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;
}
