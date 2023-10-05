package com.coralsoft.domorder.services;

import com.coralsoft.domorder.models.AddressModel;
import com.coralsoft.domorder.models.UserModel;

import java.util.UUID;

public interface UserService {

    UserModel saveUser(UserModel user);

    UserModel findUserById(UUID userId);

    void deleteUserById(UUID userId);

    AddressModel updateAddress(UUID userId, AddressModel address);
}
