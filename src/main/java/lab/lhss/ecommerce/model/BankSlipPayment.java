package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BankSlipPayment {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private Integer orderId;

    private PaymentStatus status;

    private String barCode;

}
