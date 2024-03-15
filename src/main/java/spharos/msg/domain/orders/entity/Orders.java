package spharos.msg.domain.orders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column
    private Long productId;

    @NotNull()
    @Min(0)
    @Column(columnDefinition = "integer default 0")
    private Integer ordersDeliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
