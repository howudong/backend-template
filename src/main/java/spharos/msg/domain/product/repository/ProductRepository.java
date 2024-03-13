package spharos.msg.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
