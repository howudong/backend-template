package spharos.msg.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.product.dto.ProductDto;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "랜덤 상품 조회",
    description = "랜덤으로 12개의 상품을 조회합니다")
    @GetMapping("/product-list")
    public ResponseEntity<Object> getRandomProducts(@RequestParam("state") String state, @RequestParam("index")int index) {
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("getRandomProducts 메서드 호출됨");

        try {
            ProductDto productDto = productService.getProductDto();
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "상품 조회 중 오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
