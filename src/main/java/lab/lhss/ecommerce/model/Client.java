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
@Table(name = "client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @ElementCollection
    @CollectionTable(name = "client_contacts", joinColumns = @JoinColumn(name = "client_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "description")
    private Map<ContactTypes, String> contacts;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(table = "client_details")
    private Gender gender;

    @Column(name = "birth_date", table = "client_details")
    private LocalDate birthDate;
}


