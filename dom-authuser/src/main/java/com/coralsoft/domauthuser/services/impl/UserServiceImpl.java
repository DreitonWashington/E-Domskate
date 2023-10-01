package com.coralsoft.domauthuser.services.impl;

import com.coralsoft.domauthuser.dtos.UserDto;
import com.coralsoft.domauthuser.enums.RoleType;
import com.coralsoft.domauthuser.enums.UserType;
import com.coralsoft.domauthuser.exceptions.UserNotFoundException;
import com.coralsoft.domauthuser.models.AddressModel;
import com.coralsoft.domauthuser.models.UserModel;
import com.coralsoft.domauthuser.repositories.RoleRepository;
import com.coralsoft.domauthuser.repositories.UserRepository;
import com.coralsoft.domauthuser.services.RoleService;
import com.coralsoft.domauthuser.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    RoleService roleService;

    @Override
    public boolean existsByUserName(String username){
        return userRepository.existsByUserName(username);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public UserModel saveUser(UserDto userDto) {
        var user = new UserModel();
        user.setFullName(userDto.getFullName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserType(UserType.CLIENT);

        var role = roleService.findByRoleName(RoleType.ROLE_USER.toString());
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }

    @Override
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public void deleteById(UUID userId) {
        this.findById(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserModel updateUser(UserDto user) {
        UserModel userDb = this.findById(user.getUserId());
        BeanUtils.copyProperties(user, userDb, "password");
        return userRepository.save(userDb);
    }

    @Override
    public UserModel updateAddress(UUID userId, AddressModel addressModel) {
        var user = this.findById(userId);
        user.setAddress(addressModel);
        return userRepository.save(user);
    }
}
