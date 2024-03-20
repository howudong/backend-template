package spharos.msg.domain.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.msg.domain.users.dto.NewAddressRequestDto;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String addressName;

    @NotBlank
    private String recipient;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String addressPhoneNumber;

    @NotBlank
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    public static Address addressConverter(NewAddressRequestDto newAddressRequestDto) {
        return Address
            .builder()
            .addressName(newAddressRequestDto.getAddressName())
            .recipient(newAddressRequestDto.getRecipient())
            .mobileNumber(newAddressRequestDto.getMobileNumber())
            .addressPhoneNumber(newAddressRequestDto.getAddressPhoneNumber())
            .address(newAddressRequestDto.getAddress())
            .users(newAddressRequestDto.getUsers())
            .build();
    }
}
