package com.coralsoft.domauthuser.controllers;

import com.coralsoft.domauthuser.dtos.UserDto;
import com.coralsoft.domauthuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    final
    UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){
        if(userService.existsByUserName(userDto.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: User with username -> "
                     + userDto.getUserName() + " already in use!");
        }
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: User with email -> "
                    + userDto.getEmail() + " already in use!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }
}
