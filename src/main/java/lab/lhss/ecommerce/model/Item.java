package lab.lhss.ecommerce.model;

import lab.lhss.ecommerce.listener.GenericListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners({GenericListener.class})
@Getter
@Setter
@Table(name = "item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modify_date", insertable = false)
    private LocalDateTime lastModifyDate;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "item_category",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToOne(mappedBy = "item")
    private Inventory inventory;
}
