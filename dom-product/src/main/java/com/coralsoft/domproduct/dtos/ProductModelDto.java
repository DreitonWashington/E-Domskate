package com.coralsoft.domproduct.dtos;

import com.coralsoft.domproduct.enums.TypeClothes;
import com.coralsoft.domproduct.enums.TypeShoes;
import com.coralsoft.domproduct.models.BrandModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ProductModelDto {

    public interface ProductView{
        public static interface publisher{}
    }

    @JsonView({ProductView.publisher.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;
    @JsonView({ProductView.publisher.class})
    private String name;
    private BrandModel brand;
    private String description;
    @JsonView({ProductView.publisher.class})
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<SizeModelDto> sizes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ImageModelDto> images;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TypeClothes typeClothes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TypeShoes typeShoes;
}
