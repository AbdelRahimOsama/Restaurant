package com.spring.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerDetails_seq")
    @SequenceGenerator(
            name = "CustomerDetails_seq",
            sequenceName = "CUSTOMER_DETAILS_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String email;

    private String phone;

    private Integer age;

    private String address;

    private String imagePath;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
