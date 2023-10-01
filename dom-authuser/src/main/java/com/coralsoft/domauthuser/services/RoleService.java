package com.coralsoft.domauthuser.services;

import com.coralsoft.domauthuser.enums.RoleType;
import com.coralsoft.domauthuser.models.RoleModel;

public interface RoleService {
    RoleModel findByRoleName(String role);
}
