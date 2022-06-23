package lab.lhss.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@SecondaryTable(name = "client_details", pkJoinColumns = @PrimaryKeyJoinColumn(name =  "client_id"))
@Getter
@Setter
@Table(name = "client",
        uniqueConstraints = @UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"}),
        indexes = { @Index(name = "idx_name", columnList = "name") })
public class Client extends IntegerBaseEntity {

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @ElementCollection
    @CollectionTable(name = "client_contacts",
            joinColumns = @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client_contacts_client")))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "description")
    private Map<ContactTypes, String> contacts;

    @Column(length = 100, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @Column(length = 14, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(table = "client_details", length = 30, nullable = false)
    private Gender gender;

    @Column(name = "birth_date", table = "client_details")
    private LocalDate birthDate;
}


