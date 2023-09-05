package com.coralsoft.domproduct.dtos;

import com.coralsoft.domproduct.enums.TypeClothes;
import com.coralsoft.domproduct.enums.TypeShoes;
import com.coralsoft.domproduct.models.BrandModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductModelDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;
    private String name;
    private BrandModel brand;
    private String description;
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SizeModelDto> sizes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TypeClothes typeClothes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TypeShoes typeShoes;
}
