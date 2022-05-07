package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "item_id")
    @ManyToOne
    private Item item;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    private Integer amount;

}
