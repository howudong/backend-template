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
    @JoinColumn(name = "usersId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Builder
    public ProductLike(Users users, Product product, boolean isLike) {
        this.users = users;
        this.product = product;
        this.isLike = isLike;
    }

}
