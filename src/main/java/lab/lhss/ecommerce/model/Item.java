package lab.lhss.ecommerce.model;

import lab.lhss.ecommerce.listener.GenericListener;
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
@NamedQueries({
        @NamedQuery(name = "Item.listAll", query = "select i from Item i"),
        @NamedQuery(name = "Item.listByCategory", query = "select i from Item i where exists (select 1 from Category c join c.items i2 where i2 = i and c.id = :category)")
})
@Table(name = "item",
        uniqueConstraints = @UniqueConstraint(name = "unq_name", columnNames = {"name"} ),
        indexes = { @Index(name = "idx_name", columnList = "name") })
public class Item extends IntegerBaseEntity {

    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modify_date", insertable = false)
    private LocalDateTime lastModifyDate;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(275) default 'description'")
    private String description;

    private BigDecimal price;

    @Lob
    private byte[] picture;

    @ElementCollection
    @CollectionTable(name = "item_tag", joinColumns = @JoinColumn(name = "item_id",
        foreignKey = @ForeignKey(name = "fk_item_tag_item")))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "item_attribute", joinColumns = @JoinColumn(name = "item_id",
        foreignKey = @ForeignKey(name = "fk_item_attribute_item")))
    private List<Attribute> attributes;

    @ManyToMany
    @JoinTable(name = "item_category",
            joinColumns = @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_category_item")),
            inverseJoinColumns = @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_item_category_category")),
            uniqueConstraints = @UniqueConstraint(name = "unq_item_category", columnNames = {"item_id", "category_id"}))
    private List<Category> categories;

    @OneToOne(mappedBy = "item")
    private Inventory inventory;
}
