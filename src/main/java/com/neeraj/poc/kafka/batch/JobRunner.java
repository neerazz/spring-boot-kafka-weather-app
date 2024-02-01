package com.neeraj.poc.kafka.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job importWetherJob;

    public JobRunner(JobLauncher jobLauncher, Job importWetherJob) {
        this.jobLauncher = jobLauncher;
        this.importWetherJob = importWetherJob;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(importWetherJob, new JobParameters());
    }
}

