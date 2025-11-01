package com.jobportal.job_portal_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {
    private int applicationId;
    private String name;
    private String email;
    private String coverLetter;
    private String resumeFileName;
    private String resumeFilePath;
    private LocalDateTime appliedAt;
    private int jobId;
    private String jobProfile;
}
