package com.jobportal.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jobportal.entity.*;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.*;
@Service
public class JobApplicationService {
 private final JobApplicationRepository applications; private final JobRepository jobs;
 public JobApplicationService(JobApplicationRepository applications,JobRepository jobs){this.applications=applications;this.jobs=jobs;}
 @Transactional
 public JobApplication apply(Long jobId,JobApplication application){
  Job job=jobs.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+jobId));
  if(!"OPEN".equalsIgnoreCase(job.getStatus())) throw new IllegalArgumentException("This job is not open for applications");
  if(applications.existsByJobIdAndApplicantEmailIgnoreCase(jobId,application.getApplicantEmail())) throw new IllegalArgumentException("This email has already applied for this job");
  application.setId(null);application.setJob(job);application.setStatus("SUBMITTED");return applications.save(application);
 }
 public List<JobApplication> getAll(){return applications.findAll();}
 public JobApplication getById(Long id){return applications.findById(id).orElseThrow(()->new ResourceNotFoundException("Application not found with id: "+id));}
 public List<JobApplication> byEmail(String email){return applications.findByApplicantEmailIgnoreCase(email);}
 public List<JobApplication> byJob(Long jobId){if(!jobs.existsById(jobId))throw new ResourceNotFoundException("Job not found with id: "+jobId);return applications.findByJobId(jobId);}
 public JobApplication updateStatus(Long id,String status){
  JobApplication a=getById(id);String s=status==null?"":status.trim().toUpperCase();
  if(!List.of("SUBMITTED","UNDER_REVIEW","SHORTLISTED","REJECTED","HIRED").contains(s))throw new IllegalArgumentException("Invalid status. Use SUBMITTED, UNDER_REVIEW, SHORTLISTED, REJECTED, or HIRED");
  a.setStatus(s);return applications.save(a);
 }
}