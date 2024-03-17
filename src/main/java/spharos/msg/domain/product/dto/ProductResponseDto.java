package spharos.msg.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductResponseDto {

    private List<ProductInfoDto> cosmeticList;
    private List<ProductInfoDto> randomList;
    private List<ProductInfoDto> foodList;

    @Builder
    private ProductResponseDto(List<ProductInfoDto> cosmeticList, List<ProductInfoDto> randomList, List<ProductInfoDto> foodList) {
        this.cosmeticList = cosmeticList;
        this.randomList = randomList;
        this.foodList = foodList;
    }
}
