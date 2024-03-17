package spharos.msg.domain.orders.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class OrderProductDto {
        private String loginId;
        private String username;
        private String address;
        private String phoneNumber;
        private Long deliveryFee;
        private Long productPrice;
        private Long salePrice;
    }
}
