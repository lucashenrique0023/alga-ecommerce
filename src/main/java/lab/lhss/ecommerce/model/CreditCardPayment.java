package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "credit_card")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditCardPayment {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    private String number;

}
