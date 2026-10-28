package com.jobportal.entity;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="job_applications",uniqueConstraints=@UniqueConstraint(name="uk_job_applicant_email",columnNames={"job_id","applicant_email"}))
public class JobApplication {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank(message="Applicant name is required") @Column(nullable=false) private String applicantName;
 @NotBlank(message="Applicant email is required") @Email(message="Enter a valid email address") @Column(name="applicant_email",nullable=false) private String applicantEmail;
 private String resumeUrl;
 @Column(nullable=false) private String status="SUBMITTED";
 @CreationTimestamp private LocalDateTime appliedAt;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="job_id",nullable=false) @JsonIgnore private Job job;
 public JobApplication() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getApplicantName(){return applicantName;} public void setApplicantName(String applicantName){this.applicantName=applicantName;}
 public String getApplicantEmail(){return applicantEmail;} public void setApplicantEmail(String applicantEmail){this.applicantEmail=applicantEmail;}
 public String getResumeUrl(){return resumeUrl;} public void setResumeUrl(String resumeUrl){this.resumeUrl=resumeUrl;}
 public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
 public LocalDateTime getAppliedAt(){return appliedAt;} public void setAppliedAt(LocalDateTime appliedAt){this.appliedAt=appliedAt;}
 public Job getJob(){return job;} public void setJob(Job job){this.job=job;}
}