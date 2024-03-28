package spharos.msg.domain.users.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicationCheckRequestDto {
    private String loginId;
}
