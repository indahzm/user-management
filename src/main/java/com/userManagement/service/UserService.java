package com.userManagement.service;

import com.userManagement.entity.UserEntity;
import com.userManagement.entity.model.UpdatePasswordRequest;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity save(UserEntity user);
    UserEntity findById(String id);

    UserEntity findByUsername(String username);
    void deleteById (String id);
    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

}
