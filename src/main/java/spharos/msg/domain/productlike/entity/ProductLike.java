package spharos.msg.domain.productlike.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.users.entity.Users;

@Entity
@Getter
@NoArgsConstructor
public class ProductLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long likeId;
    @Column(columnDefinition = "boolean default false")
    private boolean isLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Builder
    public ProductLike(Users user, Product product, boolean isLike) {
        this.user = user;
        this.product = product;
        this.isLike = isLike;
    }

}
