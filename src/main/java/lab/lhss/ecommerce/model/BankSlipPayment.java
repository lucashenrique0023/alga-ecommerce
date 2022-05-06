package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bank_slip")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BankSlipPayment {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    private PaymentStatus status;

    private String barCode;

}
