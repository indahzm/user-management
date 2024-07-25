package com.userManagement.controller;
import com.userManagement.entity.UserEntity;
import com.userManagement.entity.model.UpdatePasswordRequest;
import com.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> userEntity = userService.findAll();
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable(required = true) String id) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable(required = true) String id, @RequestBody UserEntity user) {
        user.setId(id);
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(required = true) String id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User delete successfully");
    }

    @PostMapping("/edit-password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable(required = true) String id, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        updatePasswordRequest.setId(id);
        userService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok("Password is updated successfully");
    }
}
