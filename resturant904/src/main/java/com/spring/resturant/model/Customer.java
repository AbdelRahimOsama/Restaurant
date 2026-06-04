package com.spring.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "customer_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_seq"
    )
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ContactInfo> contactInfos;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(mappedBy = "customer")
    private CustomerDetails customerDetails;
}