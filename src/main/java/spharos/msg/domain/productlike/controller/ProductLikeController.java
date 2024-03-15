package spharos.msg.domain.productlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.productlike.service.ProductLikeService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class ProductLikeController {
    private final ProductLikeService productLikeService;
    @PostMapping("/{productId}/{userId}")
    private ApiResponse<?> likeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId){
        return productLikeService.likeProduct(productId,userId);
    }
    @DeleteMapping("/{productId}/{userId}")
    private ApiResponse<?> deleteLikeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId){
        return productLikeService.deleteLikeProduct(productId,userId);
    }
    @GetMapping("/{userId}")
    private ApiResponse<?> getProductLikeList(
            @PathVariable Long userId
            ){
        return productLikeService.getProductLikeList(userId);
    }
}