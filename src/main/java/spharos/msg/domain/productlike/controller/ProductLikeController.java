package spharos.msg.domain.productlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.productlike.service.ProductLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class ProductLikeController {
    private final ProductLikeService likeService;
    @PostMapping("/{productId}/{userId}")
    private void likeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId){
        likeService.likeProduct(productId,userId);
    }
    @DeleteMapping("/{productId}/{userId}")
    private void deleteLikeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId) {
        likeService.deleteLikeProduct(productId, userId);
    }
}
