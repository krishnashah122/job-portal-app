package com.jobportal.job_portal_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
public class JobPostDTO {

    @Id
    private int jobId;
    private String jobProfile;
    private String jobDesc;
    private int requiredExperience;
    private List<String> techStack;

}
