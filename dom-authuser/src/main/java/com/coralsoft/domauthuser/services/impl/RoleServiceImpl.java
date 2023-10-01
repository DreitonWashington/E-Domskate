package com.coralsoft.domauthuser.services.impl;

import com.coralsoft.domauthuser.exceptions.RoleNotFoundException;
import com.coralsoft.domauthuser.models.RoleModel;
import com.coralsoft.domauthuser.repositories.RoleRepository;
import com.coralsoft.domauthuser.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleModel findByRoleName(String role) {
        if(roleRepository.findByRoleName(role) == null){
            throw new RoleNotFoundException(role);
        }
        return roleRepository.findByRoleName(role);
    }
}
