package spharos.msg.domain.product.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.dto.ProductDetailInfoDto;
import spharos.msg.domain.product.dto.ProductInfoDto;
import spharos.msg.domain.product.dto.ProductResponseDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.entity.ProductSalesInfo;
import spharos.msg.domain.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import spharos.msg.domain.product.repository.ProductSalesInfoRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSalesInfoRepository productSalesInfoRepository;
    //Home화면 상품 조회
    public ProductResponseDto getHomeProducts() {
        //뷰티 상품 조회
        List<Product> beautyProducts = productRepository.findProductsByCategoryName("뷰티");
        //랜덤 상품 조회
        List<Product> randomProducts = productRepository.findRandomProducts(12);
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

    public ProductDetailInfoDto getProductDetail(Long productId) {
        log.info("ProductDetail Service 호출");
        //id값으로 상품 조회
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<ProductSalesInfo> productSalesInfoOptional = productSalesInfoRepository.findById(productId);
        //해당 객체가 존재하는지 확인 후, Dto 매핑
        if (productOptional.isPresent() && productSalesInfoOptional.isPresent()) {
            Product product = productOptional.get();
            ProductSalesInfo productSalesInfo = productSalesInfoOptional.get();

            ProductDetailInfoDto test = ProductDetailInfoDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productBrand(product.getProductBrand())
                .defaultImageIndex(product.getDefaultImageIndex())
                .discountRate(product.getDiscountRate())
                .productStars(productSalesInfo.getProductStars())
                .productReviewCount(productSalesInfo.getReviewCount())
                .build();
            log.info("dto확인"+test);
            return test;
        }
        return null;
    }

    //상품 엔티티를 상품정보Dto로 매핑하는 메서드
    private ProductInfoDto mapToProductInfoDto(Product product) {
        return ProductInfoDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .discountRate(product.getDiscountRate())
                .build();
    }
}
