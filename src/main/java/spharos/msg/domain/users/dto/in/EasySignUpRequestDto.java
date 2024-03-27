package spharos.msg.domain.users.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EasySignUpRequestDto {
    private String email;
    private String oauth_name;
    private String oauth_id;
}
