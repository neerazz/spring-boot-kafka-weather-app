package com.neeraj.poc.kafka.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobLauncher jobLauncher;
    private final Job importWetherJob;

    public JobController(JobLauncher jobLauncher, Job importWetherJob) {
        this.jobLauncher = jobLauncher;
        this.importWetherJob = importWetherJob;
    }

    @GetMapping("/startjob")
    public String startJob() {
        try {
            jobLauncher.run(importWetherJob, new JobParameters());
            return "Job started successfully";
        } catch (Exception e) {
            return "Failed to start job";
        }
    }
}
