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
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactInfo_seq")
    @SequenceGenerator(
            name = "contactInfo_seq",
            sequenceName = "CONTACT_INFO_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String name ;

    private String email;

    private String subject;

    private String message;

    @ManyToOne
    private Customer customer;
}
