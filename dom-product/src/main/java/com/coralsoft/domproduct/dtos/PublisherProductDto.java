package com.coralsoft.domproduct.dtos;

import com.coralsoft.domproduct.enums.ActionType;
import com.coralsoft.domproduct.models.ClothesModel;
import com.coralsoft.domproduct.models.ProductModel;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class PublisherProductDto {

    private UUID productId;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    public PublisherProductDto convertProductModelToPublisherProductDto(ProductModel productModel){
        this.productId = productModel.getId();
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        return this;
    }

}
