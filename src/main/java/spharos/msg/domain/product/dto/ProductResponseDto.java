package spharos.msg.domain.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class ProductResponseDto {

    @Builder
    public static class HomeCosmeRandomFood {

        private List<ProductInfoDto> cosmeticList;
        private List<ProductInfoDto> randomList;
        private List<ProductInfoDto> foodList;
    }

    @Builder
    public static class HomeFashion {

        private List<ProductInfoDto> fashionList;
    }
}
