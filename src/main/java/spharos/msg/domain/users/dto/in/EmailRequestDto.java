package spharos.msg.domain.users.dto.in;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {
    @Email
    private String email;
}
