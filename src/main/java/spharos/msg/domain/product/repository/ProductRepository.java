package spharos.msg.domain.product.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spharos.msg.domain.product.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //카테고리 별 상품 반환
    @Query("SELECT cp.product FROM CategoryProduct cp WHERE cp.category.categoryName = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);
    //랜덤 상품 반환
    @Query(value = "SELECT * FROM product ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Product> findRandomProducts();
}