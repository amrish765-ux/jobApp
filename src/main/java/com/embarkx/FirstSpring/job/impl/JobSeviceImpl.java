package com.embarkx.FirstSpring.job.impl;

import com.embarkx.FirstSpring.job.Job;
import com.embarkx.FirstSpring.job.JobRepository;
import com.embarkx.FirstSpring.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeviceImpl implements JobService {
//    private List<Job>jobs=new ArrayList<>();
    JobRepository jobRepository;

    public JobSeviceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId=1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        for(Job job:jobs){
//            if(job.getId().equals(id)) {
//                return job;
//            }
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);

    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator it=jobs.iterator();
//        while (it.hasNext()){
//            Job job=(Job) it.next();
//            if(job.getId().equals(id)){
//                it.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        for(Job job:jobs){
//            if (job.getId().equals(id)){
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//        }
//        return false;
        Optional<Job>jobOptional=jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job=jobOptional.get();
            job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
        }
        return false;
    }

}
