package spharos.msg.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.dto.ProductInfoDto;
import spharos.msg.domain.product.dto.ProductResponseDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    //Home화면 상품 조회
    public ProductResponseDto.Home1 getHome1Products() {
        log.info("getHome1Products 메서드 실행");
        //뷰티 상품들 조회
        List<Product> beautyProducts = productRepository.findProductsByCategoryName("뷰티");
        //랜덤 상품 조회
        List<Product> randomProducts = productRepository.findRandomProducts();
        //신선식품 상품 조회
        List<Product> foodProducts = productRepository.findProductsByCategoryName("신선식품");

        //ProductInfo Dto가 담긴 리스트로 변환
        List<ProductInfoDto> beautys = beautyProducts.stream()
            .limit(6)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        List<ProductInfoDto> randoms = randomProducts.stream()
            .limit(12)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        List<ProductInfoDto> foods = foodProducts.stream()
            .limit(12)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        return ProductResponseDto.Home1.builder()
            .cosmeticList(beautys)
            .randomList(randoms)
            .foodList(foods)
            .build();
    }

    public ProductResponseDto.Home2 getHome2Products(int index) {
        //pageble 객체 생성
        Pageable pageable = PageRequest.of(index, 16);
        //index 기반 패션 상품들 조회
        Page<Product> fashionProductsPage = productRepository.findFashionProducts(pageable);
        List<Product> fashionProducts = fashionProductsPage.getContent();

        // 조회된 상품들 ProductInfoDto 리스트로 변환
        List<ProductInfoDto> fashions = fashionProducts.stream()
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        return ProductResponseDto.Home2.builder()
            .fashionList(fashions)
            .build();
    }

    //Product 엔티티를 ProductInfo Dto로 매핑하는 메서드
    private ProductInfoDto mapToProductInfoDto(Product product) {
        return ProductInfoDto.builder()
            .productId(product.getId())
            .productName(product.getProductName())
            .productPrice(product.getProductPrice())
            .discountRate(product.getDiscountRate())
            .build();
    }
}
