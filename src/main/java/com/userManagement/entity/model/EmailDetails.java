package com.userManagement.entity.model;

import lombok.Data;

@Data
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private String subject;

    private String user;
    private String endDate;

}
