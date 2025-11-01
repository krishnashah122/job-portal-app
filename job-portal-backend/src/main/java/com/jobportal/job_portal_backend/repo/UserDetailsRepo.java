package com.jobportal.job_portal_backend.repo;

import com.jobportal.job_portal_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

}
