package spharos.msg.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.product.entity.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption,Long> {
}
