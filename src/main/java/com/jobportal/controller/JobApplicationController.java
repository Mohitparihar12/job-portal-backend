package com.jobportal.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.jobportal.entity.JobApplication;
import com.jobportal.service.JobApplicationService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
 private final JobApplicationService service;
 public JobApplicationController(JobApplicationService service){this.service=service;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public JobApplication apply(@RequestParam Long jobId,@Valid @RequestBody JobApplication application){return service.apply(jobId,application);}
 @GetMapping public List<JobApplication> all(){return service.getAll();}
 @GetMapping("/{id}") public JobApplication one(@PathVariable Long id){return service.getById(id);}
 @GetMapping("/by-applicant") public List<JobApplication> byApplicant(@RequestParam String email){return service.byEmail(email);}
 @GetMapping("/by-job/{jobId}") public List<JobApplication> byJob(@PathVariable Long jobId){return service.byJob(jobId);}
 @PatchMapping("/{id}/status") public JobApplication status(@PathVariable Long id,@RequestParam String status){return service.updateStatus(id,status);}
}