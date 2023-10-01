package com.coralsoft.domauthuser.repositories;

import com.coralsoft.domauthuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}
