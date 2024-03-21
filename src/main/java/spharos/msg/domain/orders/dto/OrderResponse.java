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
        private Long totalPrice;
        List<PriceInfo> priceInfos;
    }

    @Getter
    @AllArgsConstructor
    public static class PriceInfo {

        private int deliveryFee;
        private int productPrice;
        private Long salePrice;
    }
}
