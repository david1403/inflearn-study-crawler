package io.springbatch.inflearnstudycrawlerbatch.batch.tasklet;

import io.springbatch.inflearnstudycrawlerbatch.common.crawling.InflearnCrawler;
import io.springbatch.inflearnstudycrawlerbatch.common.messaging.NotificationSendingService;
import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import io.springbatch.inflearnstudycrawlerbatch.common.model.service.InflearnStudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InflearnCrawlingTasklet implements Tasklet {
    private final InflearnCrawler inflearnCrawler;
    private final InflearnStudyService inflearnStudyService;
    private final NotificationSendingService notificationSendingService;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // read items from inflearn web server
        // if exist update
        // if not exist, then insert and send notification
        List<InflearnStudyDto> extractedStudies = inflearnCrawler.getStudies();
        for (InflearnStudyDto dto : extractedStudies) {
            if (inflearnStudyService.checkExist(dto)) {
                log.info("study already exists. code: {}", dto.getStudyCode());
                inflearnStudyService.updateStudyInfo(dto);
            } else {
                inflearnStudyService.insertStudy(dto);
                //notificationSendingService.sendSms(dto);
            }
        }

        return RepeatStatus.FINISHED;
    }
}
