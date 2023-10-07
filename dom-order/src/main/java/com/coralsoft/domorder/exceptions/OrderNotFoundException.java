package com.coralsoft.domorder.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String message){
        super(message);
    }

    public OrderNotFoundException(UUID orderId){
        this(String.format("Order with ID '%s' Not Found!", orderId));
    }
}
