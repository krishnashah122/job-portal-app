package com.jobportal.job_portal_backend.service;

import com.jobportal.job_portal_backend.model.JobApplicationDTO;
import com.jobportal.job_portal_backend.model.JobPost;
import com.jobportal.job_portal_backend.model.JobPostDTO;
import com.jobportal.job_portal_backend.repo.JobRepo;
import com.jobportal.job_portal_backend.model.JobApplication;
import com.jobportal.job_portal_backend.repo.JobApplicationRepo;
import com.jobportal.job_portal_backend.utils.JobPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class JobService {

    private static final String UPLOAD_DIR = "uploads/resumes/";

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    public JobRepo getJobRepo() {
        return jobRepo;
    }

    public void setJobRepo(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    public JobPostDTO addJob(JobPost job){
        return JobPostMapper.toJobPostDTO(jobRepo.save(job));
    }

    public List<JobPostDTO> getJobs() {
        List<JobPost> jobs = jobRepo.findAll();
        List<JobPostDTO> jobPostDTOs = new ArrayList<>();
        for (JobPost job : jobs) {
            jobPostDTOs.add(JobPostMapper.toJobPostDTO(job));
        }
        return jobPostDTOs;
    }

    public Optional<JobPostDTO> getJob(int jobId) {
        Optional<JobPost> jobOpt = jobRepo.findById(jobId);
        return jobOpt.map(JobPostMapper::toJobPostDTO);
    }

    public List<JobPostDTO> searchByKeyword(String keyword) {
        List<JobPost> jobs = jobRepo.findByJobProfileIgnoreCaseContainingOrJobDescContaining(keyword, keyword);
        List<JobPostDTO> jobPostDTOs = new ArrayList<>();
        for (JobPost job : jobs) {
            jobPostDTOs.add(JobPostMapper.toJobPostDTO(job));
        }
        return jobPostDTOs;
    }

    public JobPostDTO updateJob(int jobId, JobPost jobPost) {
        if (!jobRepo.existsById(jobId)) {
            throw new NoSuchElementException("Job not found");
        }
        jobRepo.deleteById(jobId);
        return JobPostMapper.toJobPostDTO(jobRepo.save(jobPost));
    }

    public String deleteJob(int jobId) {
        if (!jobRepo.existsById(jobId)) {
            throw new NoSuchElementException("Job not found");
        }
        jobRepo.deleteById(jobId);
        return "Job Deleted Successfully";
    }

    public void load() {
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(
                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                        List.of("HTML", "CSS", "JavaScript", "React")),
                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),
                new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),
                new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                        List.of("iOS Development", "Android Development", "Mobile App"))
        ));

        jobRepo.saveAll(jobs);
    }

    public String saveApplication(int jobId, String name, String email, String coverLetter, MultipartFile resumeFile) throws IOException {
        Optional<JobPost> jobPost = jobRepo.findById(jobId);

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + resumeFile.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, resumeFile.getBytes());

        JobApplication application = new JobApplication();
        application.setName(name);
        application.setEmail(email);
        application.setCoverLetter(coverLetter);
        application.setResumeFileName(fileName);
        application.setResumeFilePath(filePath.toString());

        if (!jobPost.isEmpty()) {
            application.setJob(jobPost.get());
        }

        jobApplicationRepo.save(application);

        return "Application submitted successfully";
    }

    public List<JobApplicationDTO> getAllApplications() {
        List<JobApplication> applications = jobApplicationRepo.findAll();
        List<JobApplicationDTO> jobApplicationDTOs = new ArrayList<>();

        for (JobApplication application : applications) {
            JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
            jobApplicationDTO.setApplicationId(application.getApplicationId());
            jobApplicationDTO.setName(application.getName());
            jobApplicationDTO.setEmail(application.getEmail());
            jobApplicationDTO.setCoverLetter(application.getCoverLetter());
            jobApplicationDTO.setResumeFileName(application.getResumeFileName());
            jobApplicationDTO.setResumeFilePath(application.getResumeFilePath());
            jobApplicationDTO.setAppliedAt(application.getAppliedAt());
            jobApplicationDTO.setJobId(application.getJob().getJobId());
            jobApplicationDTO.setJobProfile(application.getJob().getJobProfile());
            jobApplicationDTOs.add(jobApplicationDTO);
        }

        return jobApplicationDTOs;
    }
}
