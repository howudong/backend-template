package spharos.msg.domain.product.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
public class ProductResponseDto {
    @Builder
    @Data
    public static class Home1 {
        private List<ProductInfoDto> cosmeticList;
        private List<ProductInfoDto> randomList;
        private List<ProductInfoDto> foodList;
    }
    @Builder
    @Data
    public static class Home2 {
        private List<ProductInfoDto> fashionList;
    }
}
