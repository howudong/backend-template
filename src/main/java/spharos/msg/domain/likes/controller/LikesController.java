package spharos.msg.domain.likes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spharos.msg.domain.likes.service.LikesService;
import spharos.msg.global.api.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikesController {
    private final LikesService likesService;
    @PostMapping("/{productId}")
    private ApiResponse<?> likeProduct(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails){
        return likesService.likeProduct(productId,userDetails.getUsername());
    }
    @DeleteMapping("/{productId}")
    private ApiResponse<?> deleteLikeProduct(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails){
        return likesService.deleteLikeProduct(productId, userDetails.getUsername());
    }
    @GetMapping
    private ApiResponse<?> getProductLikeList(
            @AuthenticationPrincipal UserDetails userDetails
            ){
        return likesService.getProductLikeList(userDetails.getUsername());
    }
}