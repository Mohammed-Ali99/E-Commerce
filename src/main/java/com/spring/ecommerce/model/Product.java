package com.spring.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "category_id")
    Category category;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_weight")
    private double weight;

    @Column(name = "product_description")
    private String description;

}
