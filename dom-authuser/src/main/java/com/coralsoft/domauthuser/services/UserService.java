package com.coralsoft.domauthuser.services;

public interface UserService {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
