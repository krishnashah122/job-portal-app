package com.jobportal.job_portal_backend.service;

import com.jobportal.job_portal_backend.dto.AuthRequest;
import com.jobportal.job_portal_backend.dto.LoginResponse;
import com.jobportal.job_portal_backend.dto.RegisterUserRequest;
import com.jobportal.job_portal_backend.dto.UserResponse;
import com.jobportal.job_portal_backend.model.Role;
import com.jobportal.job_portal_backend.model.Users;
import com.jobportal.job_portal_backend.repo.UserDetailsRepo;
import com.jobportal.job_portal_backend.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserAuthService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    public UserResponse registerUser(RegisterUserRequest registerUserRequest){
        if(userDetailsRepo.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new RuntimeException("User Already Exists");
        }

        Users user = new Users();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setRole(registerUserRequest.getRole());
        Users savedUser = userDetailsRepo.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setUsername(savedUser.getUsername());
        userResponse.setRole(savedUser.getRole());
        return userResponse;
    }

    public LoginResponse loginUser(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            Users user = userDetailsRepo.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Role role = user.getRole();

            String jwtToken = jwtUtil.generateToken(authRequest.getUsername());

            return new LoginResponse(authRequest.getUsername(), role, jwtToken);
        }catch (Exception e){
            throw e;
        }

    }

}
