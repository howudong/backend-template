package spharos.msg.domain.product.entity.option;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class OptionEtc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_etc_id")
    private Long id;

    @NotBlank
    private String productEtc;
}
