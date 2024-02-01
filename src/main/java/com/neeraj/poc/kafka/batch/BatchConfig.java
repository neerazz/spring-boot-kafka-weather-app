package com.neeraj.poc.kafka.batch;

import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import com.neeraj.poc.kafka.model.pojo.CityDTO;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final JobCompletionNotificationListener jobCompletionNotificationListener;

    public static final String CITIES_FILE_NAME = "worldcities.csv";

    @Bean
    public Job importWetherJob(Step saveToCassandra) {
        return new JobBuilder("Import WeatherDTO", jobRepository)
                .listener(jobCompletionNotificationListener)
                .start(saveToCassandra)
                .build();
    }

    @Bean
    public Step saveToCassandra(DataSourceTransactionManager dataSourceTransactionManager,
                                WeatherItemProcessor_Cas weatherItemProcessor,
                                WeatherItemWriter_Cas writer) {
        return new StepBuilder("Importing Cassandra from File and Writing to Cassandra", jobRepository)
                .<CityDTO, WeatherEntity_Cass>chunk(1000, dataSourceTransactionManager)
                .reader(new WeatherFileReader(CITIES_FILE_NAME))
                .processor(weatherItemProcessor)
                .writer(writer)
                .build();
    }
}
