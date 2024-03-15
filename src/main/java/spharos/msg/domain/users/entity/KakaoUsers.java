package spharos.msg.domain.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spharos.msg.global.entity.BaseEntity;

@Entity
@Getter
public class KakaoUsers extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_user_id")
    private Long id;
    @NotNull
    private Long userId;
}
