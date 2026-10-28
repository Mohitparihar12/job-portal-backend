package com.jobportal.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/jobs")
public class JobController {
 private final JobService service;
 public JobController(JobService service){this.service=service;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Job create(@Valid @RequestBody Job job){return service.create(job);}
 @GetMapping public List<Job> all(){return service.getAll();}
 @GetMapping("/open") public List<Job> open(){return service.getOpenJobs();}
 @GetMapping("/search") public List<Job> search(@RequestParam(required=false) String title,@RequestParam(required=false) String location,@RequestParam(required=false) String employmentType){return service.search(title,location,employmentType);}
 @GetMapping("/{id}") public Job one(@PathVariable Long id){return service.getById(id);}
 @PutMapping("/{id}") public Job update(@PathVariable Long id,@Valid @RequestBody Job job){return service.update(id,job);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}