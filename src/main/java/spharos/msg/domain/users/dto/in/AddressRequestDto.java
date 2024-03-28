package spharos.msg.domain.users.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spharos.msg.domain.users.entity.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDto {

    private String addressName;
    private String recipient;
    private String mobileNumber;
    private String addressPhoneNumber;
    private String address;
}
