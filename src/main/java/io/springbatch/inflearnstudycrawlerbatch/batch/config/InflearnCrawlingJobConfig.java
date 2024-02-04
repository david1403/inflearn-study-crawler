package io.springbatch.inflearnstudycrawlerbatch.batch.config;


import io.springbatch.inflearnstudycrawlerbatch.batch.tasklet.InflearnCrawlingTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
@ConditionalOnProperty(name = "job.name", havingValue = "inflearn_crawling")
@RequiredArgsConstructor
public class InflearnCrawlingJobConfig {
    private final InflearnCrawlingTasklet inflearnCrawlingTasklet;

    @Bean
    public Job simpleJob2(JobRepository jobRepository, Step crawlingSimpleStep) {
        return new JobBuilder("simpleJob2", jobRepository)
                .start(crawlingSimpleStep)
                .build();
    }
    @Bean
    public Step crawlingSimpleStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("simpleStep2", jobRepository)
                .tasklet(inflearnCrawlingTasklet, platformTransactionManager)
                .build();

    }
}
