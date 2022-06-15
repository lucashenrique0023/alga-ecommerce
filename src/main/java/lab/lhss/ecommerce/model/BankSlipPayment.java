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
@Table(name = "payment_bank_slip")
@DiscriminatorValue("bankslip")
public class BankSlipPayment extends Payment {

    @Column(name = "bar_code")
    private String barCode;

}
