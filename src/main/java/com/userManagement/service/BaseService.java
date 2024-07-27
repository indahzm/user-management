package com.userManagement.service;

import com.userManagement.entity.UserEntity;
import com.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    @Autowired
    private UserRepository userRepository;

    protected UserEntity getUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName()).get();
        return user;
    }
}
