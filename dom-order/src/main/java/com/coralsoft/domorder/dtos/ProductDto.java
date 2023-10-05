package com.coralsoft.domorder.dtos;

import com.coralsoft.domorder.repositories.ProductRepository;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;
    private String name;
    private double price;
    public ProductDto(UUID id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
