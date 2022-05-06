package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoice")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    private String xml;

    @Column(name = "emission_date")
    private Date emissionDate;

}
