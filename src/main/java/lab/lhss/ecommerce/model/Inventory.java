package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "inventory",
        uniqueConstraints = @UniqueConstraint(name = "unq_item", columnNames = {"item_id"}))
public class Inventory extends IntegerBaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventory_item"))
    private Item item;

    private Integer amount;
}
