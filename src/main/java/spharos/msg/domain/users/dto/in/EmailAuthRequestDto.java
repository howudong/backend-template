package spharos.msg.domain.users.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthRequestDto {

    String email;
    String secretKey;
}
