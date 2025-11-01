package com.jobportal.job_portal_backend.utils;

import com.jobportal.job_portal_backend.model.JobApplication;
import com.jobportal.job_portal_backend.model.JobApplicationDTO;

public class JobApplicationMapper {

    public static JobApplicationDTO toJobApplicationDTO(JobApplication application) {
        if (application == null) return null;
        return new JobApplicationDTO(
                application.getApplicationId(),
                application.getName(),
                application.getEmail(),
                application.getCoverLetter(),
                application.getResumeFileName(),
                application.getResumeFilePath(),
                application.getAppliedAt(),
                application.getJob().getJobId(),
                application.getJob().getJobProfile()
        );
    }
}
