package com.coralsoft.domorder.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(UUID productId){
        this(String.format("Product with ID '%s' Not Found!", productId));
    }
}
