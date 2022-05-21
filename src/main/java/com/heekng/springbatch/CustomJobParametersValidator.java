package com.heekng.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {

        /*
            job이 수행되기 전에 repository 단계에서 기능 시작 전 (SimpleJobLauncher)
            job이 실행되기 전에 (SimpleJob)
            실행된다.
         */
        if (parameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameters is not found");
        }
    }

}
