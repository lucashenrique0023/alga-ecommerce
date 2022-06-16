package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @MapsId("itemId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_price", nullable = false)
    private BigDecimal itemPrice;

    @Column(nullable = false)
    private Integer amount;

    @Transient
    private BigDecimal total;

    @PostLoad
    public void totalSum() {
        total = itemPrice.multiply(BigDecimal.valueOf(amount));
    }

}
