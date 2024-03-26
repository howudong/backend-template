package spharos.msg.domain.users.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spharos.msg.domain.users.dto.SignUpRequestDto;
import spharos.msg.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(min = 5, max = 20)
    private String loginId;

    private String uuid;

    @NotBlank
    private String password;

    @NotBlank()
    @Pattern(regexp = "^\\d{10,11}$")
    private String phoneNumber;

    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;

    @Column(columnDefinition = "bigint default 0")
    private Long baseAddressId;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * 현재 P/W를 단방향 암호화 시킨다.
     */
//    public static String passwordToHash(String password) {
//        return new BCryptPasswordEncoder().encode(password);
    public void passwordToHash(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return uuid를 반환함
     */
    @Override
    public String getUsername() {
        return this.uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
