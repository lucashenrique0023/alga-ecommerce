package lab.lhss.ecommerce.model;

import lab.lhss.ecommerce.listener.GenerateInvoiceListener;
import lab.lhss.ecommerce.listener.GenericListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orderr")
@EntityListeners({GenerateInvoiceListener.class, GenericListener.class})
public class Order extends IntegerBaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_client"))
    private Client client;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> items;

    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modify_date", insertable = false)
    private LocalDateTime lastModifyDate;

    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private OrderStatus status;

    @OneToOne(mappedBy = "order", orphanRemoval = true)
    private Payment payment;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    @Embedded
    private Address address;

    public boolean isPaid() {
        return this.getStatus().equals(OrderStatus.PAID);
    }

    public void calculateOrderTotal() {
        if (items != null) {
            total = items.stream().map(
                    i -> new BigDecimal(i.getAmount()).multiply(i.getItemPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        calculateOrderTotal();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifyDate = LocalDateTime.now();
        calculateOrderTotal();
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Post Persist Order.");
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("Post Update Order.");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Before  Remove Order.");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("After Remove Order.");
    }

    @PostLoad
    public void postLoad() {
        System.out.println("After Load Order.");
    }
}
