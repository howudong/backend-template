package spharos.msg.domain.likes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.likes.service.LikesService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikesController {
    private final LikesService likesService;
    @PostMapping("/{productId}/{userId}")
    private ApiResponse<?> likeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId){
        return likesService.likeProduct(productId,userId);
    }
    @DeleteMapping("/{productId}/{userId}")
    private ApiResponse<?> deleteLikeProduct(
            @PathVariable Long productId,
            @PathVariable Long userId){
        return likesService.deleteLikeProduct(productId,userId);
    }
    @GetMapping("/{userId}")
    private ApiResponse<?> getProductLikeList(
            @PathVariable Long userId
            ){
        return likesService.getProductLikeList(userId);
    }
}