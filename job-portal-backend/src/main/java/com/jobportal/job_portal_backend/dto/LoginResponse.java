package com.jobportal.job_portal_backend.dto;

import com.jobportal.job_portal_backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private Role role;
    private String jwtToken;

}
