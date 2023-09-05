package com.coralsoft.domproduct.models;

import com.coralsoft.domproduct.enums.TypeShoes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@NoArgsConstructor
@DiscriminatorValue("shoes")
@Entity
public class ShoesModel extends ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(value = EnumType.STRING)
    private TypeShoes typeShoes;
}
