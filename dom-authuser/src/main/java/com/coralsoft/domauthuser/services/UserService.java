package com.coralsoft.domauthuser.services;

import com.coralsoft.domauthuser.dtos.UserDto;
import com.coralsoft.domauthuser.models.AddressModel;
import com.coralsoft.domauthuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);

    UserModel saveUser(UserDto userDto);

    UserModel findById(UUID userId);

    void deleteById(UUID userId);

    Page<UserModel> findAll(Pageable pageable);

    UserModel updateUser(UserDto user);

    UserModel updateAddress(UUID userId, AddressModel addressModel);

    void deleteRelationUserRoleByUserId(UUID userId);
}
