package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue("bankslip")
public class BankSlipPayment extends Payment {

    @Column(name = "bar_code", length = 100)
    private String barCode;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

}
