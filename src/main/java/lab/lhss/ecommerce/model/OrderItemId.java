package lab.lhss.ecommerce.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItemId implements Serializable {

    @EqualsAndHashCode.Include
    private Integer orderId;

    @EqualsAndHashCode.Include
    private Integer itemId;

}
