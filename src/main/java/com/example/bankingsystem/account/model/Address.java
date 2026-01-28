package com.example.bankingsystem.account.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String city;
    private String postalCode;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return street.equals(address.street)
                && city.equals(address.city)
                && postalCode.equals(address.postalCode)
                && country.equals(address.country);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
