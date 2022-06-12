package lab.lhss.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "order_id")
    @EqualsAndHashCode.Include
    private Integer orderId;

    @Column(name = "item_id")
    @EqualsAndHashCode.Include
    private Integer itemId;

}
