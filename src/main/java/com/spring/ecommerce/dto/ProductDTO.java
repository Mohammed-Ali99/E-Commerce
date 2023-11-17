package com.spring.ecommerce.dto;


import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private int categoryID;
    private double price;
    private double weight;
    private String description;
}
