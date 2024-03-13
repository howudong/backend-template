package spharos.msg.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spharos.msg.domain.product.entity.Product;
import spharos.msg.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@RequiredArgsConstructor
@RestController
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
    private final ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

//    @GetMapping("/product-list/all")
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @Operation(summary = "랜덤 상품 조회",
    description = "랜덤으로 12개의 상품을 조회합니다")
    @GetMapping("/product-list")
    public List<Product> getRandom12Products(@RequestParam("param") String state, @RequestParam("index")int index) {
        if ("HOME".equals(state) && index == 0) {
            return productService.getRandom12Products();
        } else {
            throw new IllegalArgumentException("Invalid state or index");
        }
    }
}
