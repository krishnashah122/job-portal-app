package com.jobportal.job_portal_backend.controller;

import com.jobportal.job_portal_backend.dto.AuthRequest;
import com.jobportal.job_portal_backend.dto.LoginResponse;
import com.jobportal.job_portal_backend.dto.RegisterUserRequest;
import com.jobportal.job_portal_backend.dto.UserResponse;
import com.jobportal.job_portal_backend.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return new ResponseEntity<UserResponse>(userAuthService.registerUser(registerUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<LoginResponse>(userAuthService.loginUser(authRequest), HttpStatus.OK);
    }

}
