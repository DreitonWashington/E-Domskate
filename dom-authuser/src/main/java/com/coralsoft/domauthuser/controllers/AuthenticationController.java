package com.coralsoft.domauthuser.controllers;

import com.coralsoft.domauthuser.models.UserModel;
import com.coralsoft.domauthuser.repositories.UserRepository;
import com.coralsoft.domauthuser.services.UserService;
import com.coralsoft.domauthuser.services.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> registerUser(@RequestBody UserModel user){
        if(userService.existsByUsername(user.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: User with username -> "
                     + user.getUsername() + " already in use!");
        }
        return null;
    }
}
