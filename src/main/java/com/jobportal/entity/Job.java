package com.jobportal.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="jobs")
public class Job {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank(message="Job title is required") @Size(max=120) @Column(nullable=false) private String title;
 @NotBlank(message="Company name is required") @Column(nullable=false) private String companyName;
 @NotBlank(message="Location is required") @Column(nullable=false) private String location;
 @NotBlank(message="Job description is required") @Column(length=3000,nullable=false) private String description;
 @NotBlank(message="Employment type is required") @Column(nullable=false) private String employmentType;
 @NotNull(message="Salary is required") @DecimalMin(value="0.0",inclusive=false,message="Salary must be greater than zero") @Column(nullable=false) private BigDecimal salary;
 @Column(nullable=false) private String status="OPEN";
 @CreationTimestamp private LocalDateTime createdAt;
 public Job() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
 public String getCompanyName(){return companyName;} public void setCompanyName(String companyName){this.companyName=companyName;}
 public String getLocation(){return location;} public void setLocation(String location){this.location=location;}
 public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
 public String getEmploymentType(){return employmentType;} public void setEmploymentType(String employmentType){this.employmentType=employmentType;}
 public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal salary){this.salary=salary;}
 public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}