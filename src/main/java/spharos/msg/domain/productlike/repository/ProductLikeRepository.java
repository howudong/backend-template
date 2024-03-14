package spharos.msg.domain.productlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.productlike.entity.ProductLike;
import spharos.msg.domain.users.entity.Users;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    boolean existsByUsersAndProduct(Users users, Product product);
    Optional<ProductLike> findByUsersAndProduct(Users users, Product product);
    List<ProductLike> findByUsers(Users users);
}