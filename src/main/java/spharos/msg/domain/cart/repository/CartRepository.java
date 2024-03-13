package spharos.msg.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
