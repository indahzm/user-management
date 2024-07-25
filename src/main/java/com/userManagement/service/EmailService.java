package com.userManagement.service;

import com.userManagement.entity.model.EmailDetails;

public interface EmailService {
    String sendEmail(EmailDetails emailDetails);
}
