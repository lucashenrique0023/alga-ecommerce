package lab.lhss.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    private String zip;

    private String street;

    private String number;

    @Column(name = "additional_info")
    private String additionalInfo;

    private String city;

    private String state;


}
