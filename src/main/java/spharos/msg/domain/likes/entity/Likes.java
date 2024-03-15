package spharos.msg.domain.likes.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.users.entity.Users;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long likeId;
    @Column(columnDefinition = "boolean default true")
    private boolean isLike;

    @ManyToOne
    @JoinColumn(name = "usersId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Builder
    public Likes(Users users, Product product, boolean isLike) {
        this.users = users;
        this.product = product;
        this.isLike = isLike;
    }

}
