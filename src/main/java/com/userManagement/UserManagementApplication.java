package com.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UserManagementApplication {

//	@Autowired
//	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendEmail() {
//		sen
//	}
}
