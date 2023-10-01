package com.coralsoft.domauthuser.repositories;

import com.coralsoft.domauthuser.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    @Query(value = "Select *from tb_roles where role_name = :role", nativeQuery = true)
    RoleModel findByRoleName(String role);
}
