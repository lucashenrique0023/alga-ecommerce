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
@Table(name = "orderr")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Address address;

}
