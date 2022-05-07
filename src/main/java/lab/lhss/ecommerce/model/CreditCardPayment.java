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

    @Column(name = "order_id")
    private Integer orderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String number;

}
