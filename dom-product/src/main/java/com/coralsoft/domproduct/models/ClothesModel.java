package com.coralsoft.domproduct.models;

import com.coralsoft.domproduct.enums.TypeClothes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("clothes")
public class ClothesModel extends ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private TypeClothes typeClothes;
}
