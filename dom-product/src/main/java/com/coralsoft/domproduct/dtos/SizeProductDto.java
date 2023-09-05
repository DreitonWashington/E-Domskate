package com.coralsoft.domproduct.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class SizeProductDto {

    private UUID product_model_Id;
    private UUID sizeId;
    private Integer quantity;
    private String size;
}
