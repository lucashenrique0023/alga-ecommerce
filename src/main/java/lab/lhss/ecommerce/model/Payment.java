package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment extends IntegerBaseEntity {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_order"), unique = true)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private PaymentStatus status;

}
