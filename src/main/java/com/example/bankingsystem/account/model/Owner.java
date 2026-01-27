package com.example.bankingsystem.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@RequiredArgsConstructor
public class Owner {

    private String firstName;
    private String lastName;
    private String email;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="street", column=@Column(name="owner_street", nullable=false)),
        @AttributeOverride(name="city", column=@Column(name="owner_city", nullable=false)),
        @AttributeOverride(name="postalCode", column=@Column(name="owner_postal_code", nullable=false)),
        @AttributeOverride(name="country", column=@Column(name="owner_country", nullable=false)),
    })
    private Address address;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return firstName.equals(owner.firstName) &&
                lastName.equals(owner.lastName) &&
                email.equals(owner.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
