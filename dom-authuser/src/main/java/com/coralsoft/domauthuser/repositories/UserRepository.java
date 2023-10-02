package com.coralsoft.domauthuser.repositories;

import com.coralsoft.domauthuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);

    @Query(value = "delete from tb_users_roles where user_model_user_id = :userId", nativeQuery = true)
    @Modifying
    void deleteRelationUserRoleByUserId(UUID userId);

    @Query(value = "select case when count(*)>0 then 'true' else 'false' end from tb_users_roles where user_model_user_id = :userId", nativeQuery = true)
    boolean verifyIfExistsRelationUserRole(UUID userId);
}
