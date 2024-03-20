package spharos.msg.domain.users.entity;

import jakarta.persistence.*;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    public static Users usersConverter(SignUpRequestDto signUpRequestDto){
        signUpRequestDto.setPassword(hashPassword(signUpRequestDto.getPassword()));
        return Users
            .builder()
            .loginId(signUpRequestDto.getLoginId())
            .password(signUpRequestDto.getPassword())
            .userName(signUpRequestDto.getUsername())
            .email(signUpRequestDto.getEmail())
            .phoneNumber(signUpRequestDto.getPhoneNumber())
            .uuid(UUID.randomUUID().toString())
            .build();
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

    public static String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

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
