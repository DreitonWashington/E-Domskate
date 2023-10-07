package com.coralsoft.domorder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderStatusConflictException extends RuntimeException{

    public OrderStatusConflictException(String message){
        super(message);
    }

    public OrderStatusConflictException(UUID orderId, String status){
        this(String.format("Order with ID '%s' already with OrderStatus '%s'", orderId,status));
    }
}
