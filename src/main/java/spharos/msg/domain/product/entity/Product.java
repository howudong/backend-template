package spharos.msg.domain.product.entity;

import jakarta.persistence.*;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Integer productPrice;
    private boolean isDeleted;
    private String productBrand;
    private Integer defaultImageIndex;
    private Integer minDeliveryFee;
    private double discountRate;
//    @ManyToOne
//    private Vender vender_id;
}