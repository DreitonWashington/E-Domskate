package com.coralsoft.domauthuser.controllers;

import com.coralsoft.domauthuser.dtos.UserDto;
import com.coralsoft.domauthuser.models.UserModel;
import com.coralsoft.domauthuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findUserById(@PathVariable(value = "userId")UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> findAllUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable(value = "userId")UUID userId, @RequestBody @JsonView(UserDto.UserView.UserUpdate.class)UserDto user){
        user.setUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId")UUID userId){
        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }
}