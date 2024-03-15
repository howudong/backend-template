package spharos.msg.domain.orders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import spharos.msg.global.entity.BaseEntity;

@Entity
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
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
