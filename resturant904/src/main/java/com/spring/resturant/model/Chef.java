package com.spring.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chef_seq")
    @SequenceGenerator(
            name = "chef_seq",
            sequenceName = "CHEF_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String spec;

    private String logoPath;

    private String faceLink;

    private String tweLink;

    private String instaLink;
}
