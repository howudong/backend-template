package spharos.msg.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.product.dto.ProductDto;
import spharos.msg.domain.product.dto.ProductInfo;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProductDto() {
        //랜덤한 상품 정보 조회
        List<Product> randomProducts = productRepository.findRandom12Products();

        //Entity를 ProductInfo Dto로 변환
        List<ProductInfo> productInfoList = randomProducts.stream()
                .map(product -> ProductInfo.builder()
                        .productId(product.getProductId().intValue())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .discountRate((float) product.getDiscountRate())
                        .build())
                .collect(Collectors.toList());

        // ProductDto 객체 생성 및 반환
        return ProductDto.builder()
                .randomList(productInfoList)
                .build();
    }
}
