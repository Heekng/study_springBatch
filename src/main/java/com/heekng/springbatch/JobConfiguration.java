package com.heekng.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job1() {
        // --job.name=job1 name=user1
        // validator을 통해 파라미터에 대한 검증이 실행된다.
        return jobBuilderFactory.get("job1")
                .start(step1())
                .next(step2())
                .next(step3())
                .validator(new CustomJobParametersValidator())
                .build();
    }

    @Bean
    public Job job2() {
        // --job.name=job2 name=user1 date=2022/05/21 count=1
        // validator을 통해 파라미터에 대한 검증이 실행된다.
        return jobBuilderFactory.get("job2")
                .start(step1())
                .next(step2())
                .next(step3())
                .validator(
                        new DefaultJobParametersValidator(
                                new String[]{"name", "date"},
                                new String[]{"count"}
                        )
                )
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step3 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
