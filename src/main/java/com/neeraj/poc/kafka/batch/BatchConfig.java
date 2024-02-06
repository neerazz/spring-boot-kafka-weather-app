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
import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.batch.item.database.support.DefaultDataFieldMaxValueIncrementerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final DataSource dataSource;
    private final JobCompletionNotificationListener jobCompletionNotificationListener;
    private final DataSourceTransactionManager transactionManager;

    public static final String CITIES_FILE_NAME = "worldcities.csv";

    //    @Value("${spring.batch.jdbc.initialize-schema}")
//    private final String initializeSchema;
//
//    @Bean
//    public JobRepository jobRepository() {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setIsolationLevelForCreate("SERIALIZABLE");
//        factory.setTablePrefix("BATCH_");
//        factory.setMaxVarCharLength(1000);
//        factory.setIncrementerFactory(incrementerFactory());
//        try {
//            return factory.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Bean
    public DataFieldMaxValueIncrementerFactory incrementerFactory() {
        return new DefaultDataFieldMaxValueIncrementerFactory(dataSource);
    }

    @Bean
    public Job importWetherJob(Step saveToCassandra, JobRepository jobRepository) {
        return new JobBuilder("Import WeatherDTO", jobRepository)
                .listener(jobCompletionNotificationListener)
                .start(saveToCassandra)
                .build();
    }

    @Bean
    public Step saveToCassandra(JobRepository jobRepository,
                                WeatherItemProcessor_Cas weatherItemProcessor,
                                WeatherItemWriter_Cas writer) {
        return new StepBuilder("Importing Cassandra from File and Writing to Cassandra", jobRepository)
                .<CityDTO, WeatherEntity_Cass>chunk(1000, transactionManager)
                .reader(new WeatherFileReader(CITIES_FILE_NAME))
                .processor(weatherItemProcessor)
                .writer(writer)
                .build();
    }
}
