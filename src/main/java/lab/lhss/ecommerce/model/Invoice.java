package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoice")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id")
//    @JoinTable(name = "invoice_order",
//            joinColumns = @JoinColumn(name = "invoice_id", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "order_id", unique = true))
    private Order order;

    private String xml;

    @Column(name = "emission_date")
    private Date emissionDate;

}
