package com.spring.resturant.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "PRODUCT_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;


}
