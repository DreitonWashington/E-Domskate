package com.coralsoft.domproduct.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class BrandNotFoundException extends EntityNotFoundException {

    public BrandNotFoundException(String mesage){
        super(mesage);
    }

    public BrandNotFoundException(UUID id){
        this(String.format("Brand with Id -> '%s' not found!",id));
    }
}
