package com.jobportal.job_portal_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
public class JobPost {

    @Id
    private int jobId;
    private String jobProfile;
    private String jobDesc;
    private int requiredExperience;
    private List<String> techStack;
    @OneToMany(mappedBy = "job", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<JobApplication> jobApplications = new ArrayList<>();

    public JobPost(int jobId, String jobProfile, String jobDesc, int requiredExperience, List<String> techStack) {
        this.jobId = jobId;
        this.jobProfile = jobProfile;
        this.jobDesc = jobDesc;
        this.requiredExperience = requiredExperience;
        this.techStack = techStack;
        this.jobApplications = new ArrayList<>();
    }

}
