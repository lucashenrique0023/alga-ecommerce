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
@Table(name = "order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private LocalDateTime orderDate;

    private LocalDateTime conclusionDate;

    private Integer invoiceId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
