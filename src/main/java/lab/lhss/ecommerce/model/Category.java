package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table")
    @TableGenerator(name = "table", table = "hibernate_sequences",
            pkColumnName = "sequence_name",
            pkColumnValue = "category",
            valueColumnName = "next_val",
            initialValue = 0,
            allocationSize = 50
    )
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    @Column(name = "upper_category")
    private Integer upperCategory;
}
