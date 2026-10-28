package com.jobportal.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.JobApplication;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
 List<JobApplication> findByApplicantEmailIgnoreCase(String applicantEmail);
 List<JobApplication> findByJobId(Long jobId);
 boolean existsByJobIdAndApplicantEmailIgnoreCase(Long jobId,String applicantEmail);
}