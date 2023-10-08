package com.coralsoft.domproduct.dtos;

import com.coralsoft.domproduct.models.ClothesModel;
import com.coralsoft.domproduct.models.ProductModel;
import lombok.Data;

import java.util.UUID;

@Data
public class PublisherProductDto {

    private UUID productId;
    private String name;
    private double price;

    public PublisherProductDto convertProductModelToPublisherProductDto(ProductModel productModel){
        this.productId = productModel.getId();
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        return this;
    }
}
