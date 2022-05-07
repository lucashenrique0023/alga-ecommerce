package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bank_slip")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BankSlipPayment {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    private PaymentStatus status;

    @Column(name = "bar_code")
    private String barCode;

}
