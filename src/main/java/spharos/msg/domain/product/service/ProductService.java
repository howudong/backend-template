package spharos.msg.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.dto.ProductInfo;
import spharos.msg.domain.product.dto.ProductResponseDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //카테고리 상품 반환
//    public List<Product> getFashionProducts(String categoryName) {
//        return productRepository.findProductsByCategoryName(categoryName);
//    }

    // 랜덤 상품 반환
    public ProductResponseDto getHomeProducts() {
        //패션 상품 조회
        List<Product> fashionProducts = productRepository.findProductsByCategoryName("패션");
        //랜덤 상품 조회
        List<Product> randomProducts = productRepository.findRandomProducts();

        //Entity를 ProductInfo Dto로 변환
        List<ProductInfo> fashionProductList = fashionProducts.stream()
                .map(product -> ProductInfo.builder()
                        .productId(product.getId().intValue())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .discountRate(product.getDiscountRate())
                        .build())
                .collect(Collectors.toList());

        List<ProductInfo> randomProductList = randomProducts.stream()
                .map(product -> ProductInfo.builder()
                        .productId(product.getId().intValue())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .discountRate(product.getDiscountRate())
                        .build())
                .collect(Collectors.toList());

        return ProductResponseDto.builder()
                .cosmeticList(fashionProductList)
                .randomList(randomProductList)
                .build();
    }
}
