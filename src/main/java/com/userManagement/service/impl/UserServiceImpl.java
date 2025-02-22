package com.userManagement.service.impl;

import com.userManagement.entity.UserEntity;
import com.userManagement.entity.model.EmailDetails;
import com.userManagement.entity.model.UpdatePasswordRequest;
import com.userManagement.repository.UserRepository;
import com.userManagement.service.BaseService;
import com.userManagement.service.EmailService;
import com.userManagement.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) {
        UserEntity userLogin = getUserLogin();
        if(user.getId() != null) {
            UserEntity oldUser = findById(user.getId());
            user.setCreatedAt(oldUser.getCreatedAt());
            user.setCreatedById(oldUser.getCreatedById());
            user.setPassword(oldUser.getPassword());

        } else {
            Optional<UserEntity> userOpt = userRepository.findByUsername(user.getUsername());
            if(!userOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user exist");
            }

            user.setId(UUID.randomUUID().toString());
            user.setCreatedAt(new Date());
            user.setCreatedById(userLogin.getId());

            String password = RandomStringUtils.randomAlphanumeric(8);
            String passwordEncode = passwordEncoder.encode(password);
            user.setPassword(passwordEncode);
            System.out.println("password" + password);

            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getUsername());
            emailDetails.setSubject("New User Password Info");
            emailDetails.setMessageBody("Selamat, anda sudah terdaftar di aplikasi User Management. Untuk login, silakan gunakan password berikut : \n" + password);
            emailDetails.setEndDate((new Date()).toString());
            emailDetails.setUser(user.getUsername());
            emailService.sendEmail(emailDetails);

        }
        user.setUpdatedAt(new Date());
        user.setUpdatedById(userLogin.getId());
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(String id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        return userOpt.isPresent() ? userOpt.get() : userOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    @Override
    public UserEntity findByUsername(String email) {
        Optional<UserEntity> userOpt = userRepository.findByUsername(email);
        return userOpt.isPresent() ? userOpt.get() : userOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        UserEntity userLogin = getUserLogin();
        if (!userLogin.getId().equalsIgnoreCase(updatePasswordRequest.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to update this user password");
        }

        UserEntity user = findById(updatePasswordRequest.getId());
        Boolean isMatch = passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword());
        if (!isMatch) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your old password is not correct");
        }
        if(!updatePasswordRequest.getNewPassword().equalsIgnoreCase(updatePasswordRequest.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your new password and confirm password is not match, please check your input correctly.");
        }
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        user.setUpdatedAt(new Date());
        user.setUpdatedById(userLogin.getId());
        userRepository.save(user);
    }

}
