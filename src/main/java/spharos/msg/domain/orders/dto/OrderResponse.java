package spharos.msg.domain.orders.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private Long orderId;
        private String phoneNumber;
        List<ProductPrice> productPrices;
    }

    @Getter
    @AllArgsConstructor
    public static class ProductPrice {

        private Long deliveryFee;
        private Long productPrice;
        private Long salePrice;
    }
}
