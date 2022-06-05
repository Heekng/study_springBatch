package com.heekng.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class CustomItemWriter implements ItemStreamWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        items.forEach(log::warn);
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        log.warn("open");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        log.warn("update");
    }

    @Override
    public void close() throws ItemStreamException {
        log.warn("close");
    }
}
