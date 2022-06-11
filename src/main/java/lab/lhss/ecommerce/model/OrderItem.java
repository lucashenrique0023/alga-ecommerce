package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@IdClass(OrderItemId.class)
@Table(name = "order_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @Id
    @Column(name = "order_id")
    @EqualsAndHashCode.Include
    private Integer orderId;

    @Id
    @Column(name = "item_id")
    @EqualsAndHashCode.Include
    private Integer itemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "amount")
    private Integer amount;

}
