package spharos.msg.domain.product.service;

import static spharos.msg.global.api.code.status.ErrorStatus.NOT_EXIST_PRODUCT;
import static spharos.msg.global.api.code.status.SuccessStatus.PRODUCT_DETAIL_READ_SUCCESS;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spharos.msg.domain.category.entity.CategoryProduct;
import spharos.msg.domain.category.repository.CategoryProductRepository;
import spharos.msg.domain.product.dto.ProductDetailReponse;
import spharos.msg.domain.product.dto.ProductDetailReponse.ReviewDetail;
import spharos.msg.domain.product.dto.ProductResponse;
import spharos.msg.domain.product.entity.DeliveryFeeInfo;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.entity.ProductDetailImage;
import spharos.msg.domain.product.entity.ProductImage;
import spharos.msg.domain.product.entity.ProductOption;
import spharos.msg.domain.product.repository.DeliveryFeeInfoRepository;
import spharos.msg.domain.product.repository.ProductDetailImageRepository;
import spharos.msg.domain.product.repository.ProductImageRepository;
import spharos.msg.domain.product.repository.ProductOptionRepository;
import spharos.msg.domain.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import spharos.msg.domain.review.entity.Review;
import spharos.msg.domain.review.entity.ReviewImage;
import spharos.msg.domain.review.repository.ReviewImageRepository;
import spharos.msg.domain.review.repository.ReviewRepository;
import spharos.msg.global.api.ApiResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final ReviewRepository reviewRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductDetailImageRepository productDetailImageRepository;
    private final ReviewImageRepository reviewImageRepository;

    //Home화면 상품 조회
    public ProductResponse.HomeCosmeRandomFoodDto getHomeCosmeRandomFood() {

        List<Product> beautyProducts = productRepository.findProductsByCategoryName("뷰티");
        List<Product> foodProducts = productRepository.findProductsByCategoryName("신선식품");
        List<Product> randomProducts = productRepository.findRandomProducts();

        List<ProductResponse.ProductInfo> beautys = beautyProducts.stream()
            .limit(6)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        List<ProductResponse.ProductInfo> randoms = randomProducts.stream()
            .limit(12)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        List<ProductResponse.ProductInfo> foods = foodProducts.stream()
            .limit(12)
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        return ProductResponse.HomeCosmeRandomFoodDto.builder()
            .cosmeticList(beautys)
            .randomList(randoms)
            .foodList(foods)
            .build();
    }

    public ProductResponse.HomeFashionDto getHomeFashion(int index) {
        //pageble 객체 생성
        Pageable pageable = PageRequest.of(index, 16);
        //index 기반 패션 상품들 조회
        Page<Product> fashionProductsPage = productRepository.findFashionProducts(pageable);
        List<Product> fashionProducts = fashionProductsPage.getContent();

        // 조회된 상품들 ProductInfoDto 리스트로 변환
        List<ProductResponse.ProductInfo> fashions = fashionProducts.stream()
            .map(this::mapToProductInfoDto)
            .collect(Collectors.toList());

        return ProductResponse.HomeFashionDto.builder()
            .fashionList(fashions)
            .build();
    }

    @Transactional
    public ApiResponse<?> getProductDetail(Long productId) {

        //id로 상품 조회
        Optional<Product> productOptional = productRepository.findById(productId);

        //해당 객체가 존재 하는지 확인 후, Dto 매핑
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            CategoryProduct categoryProduct = categoryProductRepository.findByProduct(product);
            List<ProductOption> productOptions = productOptionRepository.findByProduct(product);
            List<Review> productReviews = reviewRepository.findByProduct(product);
            List<ProductImage> productImages = productImageRepository.findByProduct(product);
            List<ProductDetailImage> productDetailImages = productDetailImageRepository.findByProduct(product);

            List<String> imageUrls = productImages.stream().map(ProductImage::getProductImageUrl).toList();
            List<String> detailImageUrls = productDetailImages.stream().map(ProductDetailImage::getProductDetailImageUrl).toList();

            List<ProductDetailReponse.OptionDetail> options = productOptions.stream().map(productOption -> ProductDetailReponse.OptionDetail.builder()
                .optionId(productOption.getProductOptionId())
                .optionColor(productOption.getOptionColor().getProductColor())
                .optionSize(productOption.getOptionSize().getProductSize())
                .optionEtc(productOption.getOptionEtc().getProductEtc())
                .build()).toList();

            List<ReviewDetail> reviews = productReviews.stream().map(productReview -> {

                List<ReviewImage> reviewImages = reviewImageRepository.findByReview(productReview);
                List<String> reviewImageUrls = reviewImages.stream().map(ReviewImage::getReviewImageUrl).toList();

                return ProductDetailReponse.ReviewDetail.builder()
                    .reviewId(productReview.getId())
                    .reviewStar(productReview.getReviewStar())
                    .reviewCreated(productReview.getCreatedAt())
                    .reviewContent(productReview.getReviewComment())
                    .reviewer(productReview.getUserId().toString())
                    .reviewImgUrlList(reviewImageUrls)
                    .build();
            }
        ).toList();

            return ApiResponse.of(PRODUCT_DETAIL_READ_SUCCESS,
                ProductDetailReponse.ProductDetailDto.builder()
                    .productId(product.getId())
                    .productName(product.getProductName())
                    .productBrand(product.getProductBrand())
                    .productPrice(product.getProductPrice())
                    .productStars(product.getProductSalesInfo().getProductStars())
                    .productDeliveryFee(product.getDeliveryFee())
                    .minDeliveryFee(product.getMinDeliveryFee())
                    .productReviewCount(product.getProductSalesInfo().getReviewCount())
                    .discountRate(product.getDiscountRate())
                    .productOptions(options)
                    .ProductCategoryName(categoryProduct.getCategory().getParent().getCategoryName())
                    .ProductCategoryNameMid(categoryProduct.getCategory().getCategoryName())
                    .productImgUrlList(imageUrls)
                    .productDetailImgUrlList(detailImageUrls)
                    .productReviewList(reviews)
                    .build());
        }
        return ApiResponse.onFailure(NOT_EXIST_PRODUCT, null);
    }


    //product를 ProductInfo 형식으로 매핑하는 메서드
    private ProductResponse.ProductInfo mapToProductInfoDto(Product product) {
        return ProductResponse.ProductInfo.builder()
            .productId(product.getId())
            .productName(product.getProductName())
            .productPrice(product.getProductPrice())
            .discountRate(product.getDiscountRate())
            .build();
    }
}
