package com.betacom.pr.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String country;

    @Column(nullable = false, unique = true)
    private String city;

    @Column(name = "postal_code", nullable = false, unique = true)
    private Integer postalCode;

    @Column(nullable = false, unique = true)
    private String street;

    @Column(nullable = false, unique = true)
    private Integer number;
}
