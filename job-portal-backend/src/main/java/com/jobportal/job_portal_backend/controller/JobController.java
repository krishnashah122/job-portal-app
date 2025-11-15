package com.jobportal.job_portal_backend.controller;

import com.jobportal.job_portal_backend.model.JobApplicationDTO;
import com.jobportal.job_portal_backend.model.JobPost;
import com.jobportal.job_portal_backend.model.JobPostDTO;
import com.jobportal.job_portal_backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping({"/", "/home"})
    public String home(){
        System.out.println("In Home");
        return "Home";
    }

    @GetMapping("/load")
    public String load(){
        System.out.println("Loading the data");
        jobService.load();
        return "Data loaded";
    }

    @GetMapping(path = "/jobs", produces = {"application/json"})
    public List<JobPostDTO> getJobs(){
        System.out.println("Getting all the job posts");
        return jobService.getJobs();
    }

    @GetMapping(path = "/job/{jobId}", produces = {"application/json"})
    public Optional<JobPostDTO> getJob(@PathVariable("jobId") int jobId){
        System.out.println("Getting the job post with job id: " + jobId);
        return jobService.getJob(jobId);
    }

    @GetMapping(path = "/jobs/keyword/{keyword}", produces = {"application/json"})
    public List<JobPostDTO> searchByKeyword(@PathVariable("keyword") String keyword){
        System.out.println("Searching by keyword");
        return jobService.searchByKeyword(keyword);
    }

    @GetMapping(path = "/applications", produces = {"application/json"})
    @PreAuthorize("hasAuthority('VIEW_APPLICATIONS')")
    public List<JobApplicationDTO> getAllApplications() {
        return jobService.getAllApplications();
    }

    @PostMapping(path = "apply/{jobId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('APPLY_JOB')")
    public String apply(
            @PathVariable("jobId") int jobId,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam(value = "coverLetter", required = false) String coverLetter,
            @RequestPart("resume") MultipartFile resumeFile) throws IOException {

        return jobService.saveApplication(jobId, name, email, coverLetter, resumeFile);
    }

    @PostMapping(path = "/job", consumes = {"application/json"})
    @PreAuthorize("hasAuthority('CREATE_JOBPOST')")
    public JobPostDTO addJob(@RequestBody JobPost jobPost){
        System.out.println("Adding the job post");
        return jobService.addJob(jobPost);
    }

    @PutMapping(path = "/job/update/{jobId}", consumes = {"application/json"})
    @PreAuthorize("hasAuthority('UPDATE_JOBPOST')")
    public JobPostDTO updateJob(@PathVariable int jobId, @RequestBody JobPost jobPost){
        System.out.println("Updating the job post");
        return jobService.updateJob(jobId, jobPost);
    }

    @DeleteMapping("/job/{jobId}")
    @PreAuthorize("hasAuthority('DELETE_JOBPOST')")
    public String deleteJob(@PathVariable int jobId){
        System.out.println("Deleting the job post");
        return jobService.deleteJob(jobId);
    }
}
