package io.springbatch.inflearnstudycrawlerbatch.common.messaging;

import io.springbatch.inflearnstudycrawlerbatch.common.messaging.sms.SmsSendingService;
import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationSendingService {
    private final SmsSendingService smsSendingService;

    public void sendSms(InflearnStudyDto inflearnStudyDto){
        smsSendingService.sendNewStudyRegistrationSms(inflearnStudyDto);
    }
}
