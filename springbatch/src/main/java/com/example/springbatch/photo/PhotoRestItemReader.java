/* package com.example.springbatch.photo;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatch.Persona;

@Component
//@Configuration
public class PhotoRestItemReader implements ItemReader<PhotoDTO>, ItemStream {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager transactionManager;
   
    private Iterator<PhotoDTO> cache;

    @Autowired
    private PhotoProxy srv;

    @Override
    public PhotoDTO read() {
    return cache != null && cache.hasNext() ? cache.next() : null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
    cache = srv.getAll().iterator();
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
    // TODO Auto-generated method stub
    }
    
    @Override
    public void close() throws ItemStreamException {
    cache = null;
    }

    @Autowired 
    private PhotoRestItemReader photoRestItemReader;

    @Bean
    Step photoStep(JdbcCursorItemReader<Persona> personaDBItemReader) {
        String[] headers = new String[] { "id", "author", "width", "height", "url", "download_url" };
        return new StepBuilder("photoStep1", jobRepository)
        .<PhotoDTO, PhotoDTO>chunk(100, transactionManager).reader(photoRestItemReader)
        .writer(new FlatFileItemWriterBuilder<PhotoDTO>().name("photoCSVItemWriter")
        .resource(new FileSystemResource("output/photoData.csv"))
        .headerCallback(new FlatFileHeaderCallback() {
        public void writeHeader(Writer writer) throws IOException {
        writer.write(String.join(",", headers));
        }}).lineAggregator(new DelimitedLineAggregator<PhotoDTO>() {{
        setDelimiter(",");
        setFieldExtractor(new BeanWrapperFieldExtractor<PhotoDTO>() {{
        setNames(headers);
        }
        });
        }}).build())
        .build();
    }
} */