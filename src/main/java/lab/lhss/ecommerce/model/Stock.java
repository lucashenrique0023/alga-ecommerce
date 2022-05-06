package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "stock")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "product_id")
    private Integer productId;

    private Integer amount;
}
