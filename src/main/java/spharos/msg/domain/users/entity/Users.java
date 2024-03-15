package spharos.msg.domain.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotBlank
    @Size(min = 5, max = 20)
    private String loginId;

    @NotBlank()
    @Pattern(regexp = "^\\d{10,11}$")
    private String phoneNumber;
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;
}
