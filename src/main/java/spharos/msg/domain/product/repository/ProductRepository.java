package spharos.msg.domain.product.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spharos.msg.domain.product.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    @Query(value = "SELECT * FROM product WHERE is_deleted = false ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Product> findRandom12Products();
}
