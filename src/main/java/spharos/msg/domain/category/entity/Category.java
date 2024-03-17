package spharos.msg.domain.category.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import spharos.msg.global.entity.BaseEntity;

import java.util.List;

@Entity
@Getter
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotNull
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;
}
