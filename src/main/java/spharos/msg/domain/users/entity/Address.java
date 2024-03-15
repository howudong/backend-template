package spharos.msg.domain.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

}
