package spharos.msg.domain.users.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EasyLoginRequestDto {
    private String OAuthId;
    private String OAuthName;
}
