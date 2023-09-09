package com.coralsoft.domproduct.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String mesage){
        super(mesage);
    }

    public ProductNotFoundException(UUID productId){
        this(String.format("Product with ID -> '%s', not found!", productId));
    }
}
