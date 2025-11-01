package com.jobportal.job_portal_backend.utils;

import com.jobportal.job_portal_backend.model.JobPost;
import com.jobportal.job_portal_backend.model.JobPostDTO;

public class JobPostMapper {

    public static JobPostDTO toJobPostDTO(JobPost jobPost) {
        if (jobPost == null) return null;
        return new JobPostDTO(
                jobPost.getJobId(),
                jobPost.getJobProfile(),
                jobPost.getJobDesc(),
                jobPost.getRequiredExperience(),
                jobPost.getTechStack()
        );
    }

    public static JobPost toJobPost(JobPostDTO dto) {
        if (dto == null) return null;
        return new JobPost(
                dto.getJobId(),
                dto.getJobProfile(),
                dto.getJobDesc(),
                dto.getRequiredExperience(),
                dto.getTechStack()
        );
    }

}
