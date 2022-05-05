package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditCardPayment {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private Integer orderId;

    private PaymentStatus status;

    private String number;

}
