package com.heekng.springbatch.batch.chunk.processor;

import com.heekng.springbatch.batch.domain.Product;
import com.heekng.springbatch.batch.domain.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class FileItemProcessor implements ItemProcessor<ProductVO, Product> {
    @Override
    public Product process(ProductVO item) throws Exception {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(item, Product.class);
    }
}
