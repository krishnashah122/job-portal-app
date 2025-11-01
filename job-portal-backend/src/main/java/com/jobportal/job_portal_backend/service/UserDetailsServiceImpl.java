package com.jobportal.job_portal_backend.service;

import com.jobportal.job_portal_backend.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userDetailsRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

}
