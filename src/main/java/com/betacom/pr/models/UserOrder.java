package com.betacom.pr.models;

import java.util.List;

import com.betacom.pr.enums.Status;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wharehouse")
    private String wharehouse;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user; 

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(mappedBy = "userOrder")
    private List<ShoppingCart> products;
}