package spharos.msg.domain.orders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import spharos.msg.global.entity.BaseEntity;

@Entity
@Getter
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @NotNull
    @Column(columnDefinition = "bigint default 0")
    private Long totalAmount;

    @NotNull
    private Long buyerId;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    private String buyerName;

    @NotBlank
    private String buyerPhoneNumber;
}
