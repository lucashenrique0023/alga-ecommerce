package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    @Column(length = 9)
    private String zip;

    @Column(length = 100)
    private String street;

    @Column(length = 10)
    private String number;

    @Column(name = "additional_info", length = 50)
    private String additionalInfo;

    @Column(length = 50)
    private String city;

    @Column(length = 2)
    private String state;


}
