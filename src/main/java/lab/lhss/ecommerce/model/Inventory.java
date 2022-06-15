package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "inventory")
public class Inventory extends IntegerBaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer amount;
}
