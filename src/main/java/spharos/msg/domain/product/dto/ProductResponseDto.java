package spharos.msg.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductResponseDto {

    private List<ProductInfo> cosmeticList;
    private List<ProductInfo> randomList;
    private List<ProductInfo> foodList;

    @Builder
    private ProductResponseDto(List<ProductInfo> cosmeticList,List<ProductInfo> randomList,List<ProductInfo> foodList) {
        this.cosmeticList = cosmeticList;
        this.randomList = randomList;
        this.foodList = foodList;
    }
}
