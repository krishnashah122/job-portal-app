package com.jobportal.job_portal_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.job_portal_backend.model.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long>{
}
