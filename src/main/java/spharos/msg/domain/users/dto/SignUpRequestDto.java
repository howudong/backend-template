package spharos.msg.domain.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    public String login_id;
    public String username;
    public String password;
    public String address;
    public String phone_number;
    public String email;
    public Boolean is_easy;
}
