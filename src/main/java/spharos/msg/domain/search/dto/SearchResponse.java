package spharos.msg.domain.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchResponse {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class SearchProductDtos {

        @Builder.Default
        List<SearchProductDto> products = new ArrayList<>();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class SearchProductDto {

        Long productId;
        String productName;
        Integer productPrice;
        @JsonProperty("isLike")
        Boolean isLike;
        String image;
        BigDecimal productStar;
        BigDecimal discountRate;
    }
}
