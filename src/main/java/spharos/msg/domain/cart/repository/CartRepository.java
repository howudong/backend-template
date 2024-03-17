package spharos.msg.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.cart.entity.CartProduct;

public interface CartRepository extends JpaRepository<CartProduct, Long> {
}
