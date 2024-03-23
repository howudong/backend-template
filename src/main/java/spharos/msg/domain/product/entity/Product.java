package spharos.msg.domain.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull.List;
import java.util.ArrayList;
import java.util.Optional;
import lombok.Getter;
import spharos.msg.domain.likes.entity.Likes;
import spharos.msg.global.entity.BaseEntity;
import spharos.msg.global.entity.Vendor;

import java.math.BigDecimal;

@Entity
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    private String productName;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer productPrice;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @NotNull
    private String productBrand;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer defaultImageIndex;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer minDeliveryFee;

    @NotNull
    @Column(columnDefinition = "decimal default 0.0")
    private BigDecimal discountRate;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sales_info_id")
    private ProductSalesInfo productSalesInfo;
}