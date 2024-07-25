package com.userManagement.controller;

import com.userManagement.entity.model.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class LoginController {

    @PostMapping()
    private String login(@RequestBody LoginRequest loginRequest) {
        return "";
    }
}
