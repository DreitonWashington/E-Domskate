package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.exceptions.UserNotFoundException;
import com.coralsoft.domorder.models.AddressModel;
import com.coralsoft.domorder.models.UserModel;
import com.coralsoft.domorder.repositories.UserRepository;
import com.coralsoft.domorder.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public UserModel findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public void deleteUserById(UUID userId) {
        this.findUserById(userId);
        userRepository.deleteById(userId);
    }

}
