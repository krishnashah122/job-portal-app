package com.jobportal.job_portal_backend.service;

import com.jobportal.job_portal_backend.model.Role;
import com.jobportal.job_portal_backend.model.Users;
import com.jobportal.job_portal_backend.repo.UserDetailsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailsRepo.findByUsername("employer").isEmpty()){
                Users employer = new Users();
                employer.setUsername("employer");
                employer.setPassword(passwordEncoder.encode("employer123"));
                employer.setRole(Role.EMPLOYER);

                userDetailsRepo.save(employer);
                System.out.println("Employer Created");
            }

            if(userDetailsRepo.findByUsername("candidate").isEmpty()){
                Users candidate = new Users();
                candidate.setUsername("candidate");
                candidate.setPassword(passwordEncoder.encode("candidate123"));
                candidate.setRole(Role.CANDIDATE);

                userDetailsRepo.save(candidate);
                System.out.println("Candidate Created");
            }
        };
    }

}
