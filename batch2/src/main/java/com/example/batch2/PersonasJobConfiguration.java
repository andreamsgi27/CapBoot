package com.example.batch2;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.thoughtworks.xstream.security.AnyTypePermission;

@Configuration
public class PersonasJobConfiguration {
@Autowired
JobRepository jobRepository;
@Autowired
PlatformTransactionManager transactionManager;

public FlatFileItemReader<PersonaDTO> personaCSVItemReader(String fname) {
    return new FlatFileItemReaderBuilder<PersonaDTO>().name("personaCSVItemReader")
    .resource(new FileSystemResource(fname))
    .linesToSkip(1)
    .delimited()
    .names(new String[] { "id", "nombre", "apellidos", "correo", "sexo", "ip" })
    .fieldSetMapper(new BeanWrapperFieldSetMapper<PersonaDTO>() { {
    setTargetType(PersonaDTO.class);
    }})
    .build();
    }
    @Autowired
    public PersonaItemProcessor personaItemProcessor;
    @Bean
    @DependsOnDatabaseInitialization
    public JdbcBatchItemWriter<Persona> personaDBItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Persona>()
    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
    .sql("INSERT INTO personas VALUES (:id,:nombre,:correo,:ip)")
    .dataSource(dataSource)
    .build();
    }

    Step importCSV2DBStep(int index, String file, JdbcBatchItemWriter<Persona> toDB) {
        return new StepBuilder("importCSV2DBStep" + index, jobRepository)
        .<PersonaDTO, Persona>chunk(10, transactionManager)
        .reader(personaCSVItemReader(file))
        .processor(personaItemProcessor)
        .writer(toDB)
        .build();
        }
        @Bean
        public Job personasJob(PersonasJobListener listener, JdbcBatchItemWriter<Persona> 
        personaDBItemWriter) {
        return new JobBuilder("personasJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(importCSV2DBStep(1, "input/personas-1.csv", personaDBItemWriter))
        .build();
        }
}