package spharos.msg.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.dto.ProductInfoDto;
import spharos.msg.domain.product.dto.ProductResponseDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;

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
        List<ProductInfoDto> beautys = beautyProducts.stream()
                .limit(6)
                .map(this::mapToProductInfoDto)
                .collect(Collectors.toList());

        List<ProductInfoDto> randoms = randomProducts.stream()
                .map(this::mapToProductInfoDto)
                .collect(Collectors.toList());

        List<ProductInfoDto> foods = foodProducts.stream()
                .limit(12)
                .map(this::mapToProductInfoDto)
                .collect(Collectors.toList());

        return ProductResponseDto.builder()
                .cosmeticList(beautys)
                .randomList(randoms)
                .foodList(foods)
                .build();
    }

    //상품 엔티티를 상품정보Dto로 매핑하는 메서드
    private ProductInfoDto mapToProductInfoDto(Product product) {
        return ProductInfoDto.builder()
                .productId(product.getId().intValue())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .discountRate(product.getDiscountRate())
                .build();
    }
}
