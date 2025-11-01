package com.jobportal.job_portal_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
@Table(name = "applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    private String name;
    private String email;

    @Column(nullable = true)
    private String coverLetter;

    private String resumeFileName;

    @Column(nullable = false)
    private String resumeFilePath;

    private LocalDateTime appliedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobPost job;
}
