package com.org.batch;


import com.org.batch.listner.BatchExecutionListener;
import com.org.batch.processor.CustomerProcessor;
import com.org.batch.reader.FileReader;
import com.org.batch.writer.Writer;
import com.org.persistence.model.CustomerReward;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;


/**
 * @author abdul.rehman4
 * This is the main configuration file for Spring batch. As Spring batch is used for large CSV processings.
 * The batch file will run in single thread per HTTP request. We have configured the CHUNK mode, So its mean the
 * job will fetch the congfigured chunk from CSV and processes it. It will never load the whole file in memory,
 * So its mean we can process large files without any tention
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BatchExecutionListener batchExecutionListener;

    //skipCount metions that how much error can be skipped during file processing
    @Value(value = "${spring.batch.job.skipcount}")
    public int skipCount;

    //Chunck size for CSV file processing. It can be configured in .properties file
    @Value(value = "${spring.batch.job.chunk.size}")
    public int batchSize;

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<Customer, CustomerReward>chunk(batchSize)
                .reader(reader(null))
                .processor(processor())
                .writer(writer(null))
                .faultTolerant()
                .skipLimit(skipCount)
                .skip(Exception.class)
                .listener(batchExecutionListener)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Customer, CustomerReward> processor() {
        return new CustomerProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> reader(@Value("#{jobParameters[filePath]}") String filePath) {
        return new FileReader(lineMapper(),filePath,1);
    }

    @Bean
    public LineMapper<Customer> lineMapper() {
        return new com.org.batch.reader.LineMapper();
    }

    @Bean
    public JdbcBatchItemWriter<CustomerReward> writer(@NotNull DataSource dataSource) {
        return new Writer(dataSource);
    }








}
