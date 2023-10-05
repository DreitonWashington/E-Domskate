package com.coralsoft.domorder.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(UUID userId){
        this(String.format("User with ID '%s' Not Found!", userId));
    }
}
