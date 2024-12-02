package com.alpha.olsp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "address_id")
    private String addressId = UUID.randomUUID().toString();

    private String addressLine1;
    private String addressLine2;
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    private State state;

    private String postalCode;


}