package com.example;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

import com.example.batch.PersonasJobListener;
import com.example.models.Persona;
import com.example.models.PhotoDTO;

public class FTPLoadTasklet implements Tasklet, InitializingBean {

    @Autowired private PhotoRestItemReader photoRestItemReader;
    @Autowired private JobRepository jobRepository;
    @Autowired private PlatformTransactionManager transactionManager;

    private static final Logger log = LoggerFactory.getLogger(FTPLoadTasklet.class);
 private Resource source;
 public void setDirectoryResource(Resource directory) { this.source = directory; }
 public void afterPropertiesSet() throws Exception { Assert.notNull(source, "directory must be set"); }
 public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
 File dir = source.getFile();
 Assert.state(dir.isDirectory(), "No es un Directory");
 File[] files = dir.listFiles(new FilenameFilter() {
 public boolean accept(File file, String name) {
 return name.toLowerCase().endsWith(".csv");
 }
 });
 // if(files.length % 2 == 1) throw new UnexpectedJobExecutionException("Error forzado");
 for (int i = 0; i < files.length; i++) {
 Files.copy(files[i].toPath(), Paths.get("input/" + files[i].getName()), StandardCopyOption.REPLACE_EXISTING);
 log.info("Copy " + files[i].getName());
 }
 return RepeatStatus.FINISHED;
 }

 @Bean
public FTPLoadTasklet ftpLoadTasklet(@Value("${input.dir.name:./ftp}") String dir) {
FTPLoadTasklet tasklet = new FTPLoadTasklet();
tasklet.setDirectoryResource(new FileSystemResource(dir));
return tasklet;
}
@Bean
public Step copyFilesInDir(FTPLoadTasklet ftpLoadTasklet) {

return new StepBuilder("copyFilesInDir", jobRepository)
 .tasklet(ftpLoadTasklet, transactionManager)
 .build();
}
@Bean
public Job personasJob(PersonasJobListener listener, Step copyFilesInDir) {
return new JobBuilder("personasJob", jobRepository)
.incrementer(new RunIdIncrementer())
.listener(listener)
.start(copyFilesInDir)
.build();
}

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


}