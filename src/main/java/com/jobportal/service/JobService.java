package com.jobportal.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.jobportal.entity.Job;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepository;
@Service
public class JobService {
 private final JobRepository repo;
 public JobService(JobRepository repo){this.repo=repo;}
 public Job create(Job job){job.setId(null);job.setStatus("OPEN");return repo.save(job);}
 public List<Job> getAll(){return repo.findAll();}
 public List<Job> getOpenJobs(){return repo.findByStatusIgnoreCase("OPEN");}
 public Job getById(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+id));}
 public Job update(Long id,Job data){Job j=getById(id);j.setTitle(data.getTitle());j.setCompanyName(data.getCompanyName());j.setLocation(data.getLocation());j.setDescription(data.getDescription());j.setEmploymentType(data.getEmploymentType());j.setSalary(data.getSalary());return repo.save(j);}
 public void delete(Long id){repo.delete(getById(id));}
 public List<Job> search(String title,String location,String type){
  return getOpenJobs().stream().filter(j->title==null||title.isBlank()||j.getTitle().toLowerCase().contains(title.toLowerCase()))
   .filter(j->location==null||location.isBlank()||j.getLocation().toLowerCase().contains(location.toLowerCase()))
   .filter(j->type==null||type.isBlank()||j.getEmploymentType().equalsIgnoreCase(type)).toList();
 }
}