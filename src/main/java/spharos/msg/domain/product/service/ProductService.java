package spharos.msg.domain.product.service;

import lombok.RequiredArgsConstructor;
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
    //Home화면 상품 조회
    public ProductResponseDto getHomeProducts() {
        //뷰티 상품 조회
        List<Product> beautyProducts = productRepository.findProductsByCategoryName("뷰티");
        //랜덤 상품 조회
        List<Product> randomProducts = productRepository.findRandomProducts();
        //신선식품 상품 조회
        List<Product> foodProducts = productRepository.findProductsByCategoryName("신선식품");

        //Entity를 ProductInfo Dto로 변환
        List<ProductInfo> fashionProductList = beautyProducts.stream()
                .limit(6)
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

        List<ProductInfo> foodProductList = foodProducts.stream()
                .limit(12)
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
                .foodList(foodProductList)
                .build();
    }
}
