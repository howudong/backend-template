package spharos.msg.domain.productlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.productlike.entity.ProductLike;

public interface ProductLikeRepository extends JpaRepository<ProductLike,Long> {
}
