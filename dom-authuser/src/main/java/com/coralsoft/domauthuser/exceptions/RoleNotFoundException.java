package com.coralsoft.domauthuser.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class RoleNotFoundException extends EntityNotFoundException {

    public RoleNotFoundException(String mensage){
        super(String.format("Role -> %s not found!", mensage));
    }


}
