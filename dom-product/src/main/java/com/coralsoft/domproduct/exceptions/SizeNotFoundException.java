package com.coralsoft.domproduct.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class SizeNotFoundException extends EntityNotFoundException {

    public SizeNotFoundException(String mesage){
        super(mesage);
    }

    public SizeNotFoundException(UUID id){
        this(String.format("Size with Id -> '%s' not found!",id));
    }
}
