package com.jobportal.job_portal_backend.repo;

import com.jobportal.job_portal_backend.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByJobProfileIgnoreCaseContainingOrJobDescContaining(String jobProfile, String jobDesc);


//    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
//            new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
//                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
//            new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
//                    List.of("HTML", "CSS", "JavaScript", "React")),
//            new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
//                    List.of("Python", "Machine Learning", "Data Analysis")),
//            new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
//                    List.of("Networking", "Cisco", "Routing", "Switching")),
//            new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
//                    List.of("iOS Development", "Android Development", "Mobile App"))
//    ));
//
//    public String addJob(JobPost job){
//        jobs.add(job);
//        return "Job post added successfully";
//    }
//
//    public List<JobPost> getJobs() {
//        return jobs;
//    }
//
//    public JobPost getJob(int jobId) {
//
//        for(JobPost job : jobs){
//            if(job.getJobId() == jobId){
//                return job;
//            }
//        }
//
//        return null;
//    }
//
//    public String updateJob(int jobId, JobPost jobPost) {
//
//        for(JobPost job : jobs){
//            if(job.getJobId() == jobId){
//                int index = jobs.indexOf(job);
//                jobs.set(index, jobPost);
//                return "Job post updated successfully";
//            }
//        }
//
//        return "Job post not found";
//    }
//
//    public String deleteJob(int jobId) {
//
//        for(JobPost job : jobs){
//            if(job.getJobId() == jobId){
//                jobs.remove(job);
//                return "Job post deleted successfully";
//            }
//        }
//
//        return "Job post not found";
//    }
}
