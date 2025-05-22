package com.example.judoShop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {


    private Long id;

    private String title;

    private String type;

    private String description;

    private int price;

    private String city;

    private String manufacturer;
}
