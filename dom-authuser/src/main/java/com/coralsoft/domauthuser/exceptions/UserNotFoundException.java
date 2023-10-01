package com.coralsoft.domauthuser.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String mensage){
        super(mensage);
    }

    public UserNotFoundException(UUID userId){
        this(String.format("User with ID -> %s Not Found!", userId));
    }
}
