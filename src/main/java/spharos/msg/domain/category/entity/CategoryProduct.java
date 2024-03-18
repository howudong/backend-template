package spharos.msg.domain.category.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.global.entity.BaseEntity;

@Entity
@Getter
public class CategoryProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_product_id")
    private Long id;

    @NotNull
    @Max(4)
    private Integer productCategoryLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
