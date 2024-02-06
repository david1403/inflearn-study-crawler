package io.springbatch.inflearnstudycrawlerbatch.common.messaging.sms;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@PropertySource("classpath:config.properties")
@RequiredArgsConstructor
public class SmsSendingService {
    private final StudyRegistrationSmsSender studyRegistrationSmsSender;

    @Value("${X-coolSms-Sender}")
    private String COOL_SMS_RECEIVER;
    @Value("${X-coolSms-Receiver}")
    private String COOL_SMS_SENDER;

    public void sendNewStudyRegistrationSms(InflearnStudyDto inflearnStudyDto) {
        SingleMessageSentResponse response = studyRegistrationSmsSender.sendNewStudyRegistrationSms(COOL_SMS_SENDER, COOL_SMS_RECEIVER, inflearnStudyDto);
    }
}
