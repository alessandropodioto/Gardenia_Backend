package com.betacom.pr.models;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<UserOrder> orders; 
}
