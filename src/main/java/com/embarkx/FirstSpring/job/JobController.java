package com.embarkx.FirstSpring.job;

import com.embarkx.FirstSpring.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
  private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>>findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
//        Company c=job.getCompany();
        return new ResponseEntity<>("job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("jobs/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        boolean deleted=jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("job deleted succesfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/jobs/{id}")
//    @RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String>updateJob1(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if (updated)
            return new ResponseEntity<>("job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
