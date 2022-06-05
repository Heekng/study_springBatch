package com.heekng.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class ChunkConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        // --job.name=batchJob
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemWriter<? super String> itemWriter() {
        return new CustomItemWriter();
    }

    @Bean
    public CustomItemStreamReader itemReader() {
        List<String> items = new ArrayList<>(10);

        for (int i = 0; i <= 10; i++) {
            items.add(String.valueOf(i));
        }

        return new CustomItemStreamReader(items);
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    log.warn("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
