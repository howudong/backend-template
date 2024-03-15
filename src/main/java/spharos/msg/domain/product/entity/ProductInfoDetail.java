package spharos.msg.domain.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class ProductInfoDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productInfoItemsId;


    @NotBlank
    private String productInfoDetailTitle;

    @NotBlank
    private String productInfoDetailContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
