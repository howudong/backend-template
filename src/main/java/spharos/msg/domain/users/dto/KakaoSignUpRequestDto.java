package spharos.msg.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spharos.msg.domain.users.entity.Users;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSignUpRequestDto {

    private String userUuid;

    public static KakaoSignUpRequestDto kakaoSignUpRequestConverter(String Uuid){
        return KakaoSignUpRequestDto
            .builder()
            .userUuid(Uuid)
            .build();
    }
}
