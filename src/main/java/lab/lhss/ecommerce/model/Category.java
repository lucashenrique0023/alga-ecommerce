package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category",
        uniqueConstraints = @UniqueConstraint(name = "unq_name", columnNames = { "name" }))
public class Category extends IntegerBaseEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "upper_category")
    private Category upperCategory;

    @OneToMany(mappedBy = "upperCategory")
    private List<Category> lowerCategories;

    @ManyToMany(mappedBy = "categories")
    private List<Item> itens;
}
