package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
//@Table(name = "payment_credit_card")
@DiscriminatorValue("creditcard")
public class CreditCardPayment extends Payment {

    @Column(name = "card_number")
    private String cardNumber;

}
