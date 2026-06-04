package com.spring.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(
            name = "role_seq",
            sequenceName = "ROLE_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String roleName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}