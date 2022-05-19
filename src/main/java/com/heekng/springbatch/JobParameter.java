package com.heekng.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JobParameter implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("requestDate", "20200102")
//                .addString("requestDate", "20200103")
                .addString("requestDate", "20200104")
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
