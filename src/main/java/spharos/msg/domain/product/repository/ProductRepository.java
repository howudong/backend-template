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
    @Query(value = "SELECT p FROM Product p ORDER BY RAND()")
    List<Product> findRandomProducts(@Param("limit") int limit);
    //패션 상품 반환
    @Query(value = "SELECT cp.product FROM CategoryProduct cp WHERE cp.category.categoryName = '패션잡화'")
    List<Product> findFashionProducts(int index, int limit);
}