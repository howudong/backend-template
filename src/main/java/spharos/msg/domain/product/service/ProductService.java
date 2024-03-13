package spharos.msg.domain.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //전체 상품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 랜덤한 상품 12가지 조회
    public List<Product> getRandom12Products() {
        return productRepository.findRandom12Products();
    }


}
