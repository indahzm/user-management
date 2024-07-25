package com.userManagement.service.impl;

import com.userManagement.entity.model.EmailDetails;
import com.userManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmail(EmailDetails emailDetails) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(emailDetails.getRecipient());
        simpleMailMessage.setText(emailDetails.getMessageBody());
        simpleMailMessage.setSubject(emailDetails.getSubject());

        javaMailSender.send(simpleMailMessage);

        return "Mail send successfully";
    }

}
