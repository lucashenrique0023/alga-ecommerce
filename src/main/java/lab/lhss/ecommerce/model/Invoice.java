package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoice")
public class Invoice extends IntegerBaseEntity {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_invoice_order"), unique = true)
    private Order order;

    @Lob
    @Column(nullable = false)
    private byte[] xml;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "emission_date", nullable = false)
    private Date emissionDate;

}
