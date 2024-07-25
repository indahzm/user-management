package com.userManagement.entity.model;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String id;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
