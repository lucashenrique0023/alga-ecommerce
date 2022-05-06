package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class item {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

}
