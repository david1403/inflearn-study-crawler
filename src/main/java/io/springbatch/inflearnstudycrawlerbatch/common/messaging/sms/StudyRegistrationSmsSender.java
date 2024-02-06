package io.springbatch.inflearnstudycrawlerbatch.common.messaging.sms;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@PropertySource("classpath:config.properties")
public class StudyRegistrationSmsSender {
    private final String NEW_STUDY_SMS_PREFIX = "[신규 스터디]";
    private final String NEW_STUDY_SMS_SUFFIX = "이(가) 등록되었습니다.";
    private final String NEW_LINE = "\n";
    private final int MAX_LENGTH = 15;

    private final DefaultMessageService defaultMessageService;
    public StudyRegistrationSmsSender(@Value("${X-coolSms-ApiKey}") final String apiKey, @Value("${X-coolSms-SecretKey}") final String secretKey) {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, "https://api.coolsms.co.kr");
    }


    public SingleMessageSentResponse sendNewStudyRegistrationSms(String sender, String receiver, InflearnStudyDto inflearnStudyDto) {
        Message smsMessage = new Message();
        smsMessage.setFrom(sender);
        smsMessage.setTo(receiver);
        smsMessage.setText(createTextBody(inflearnStudyDto.getStudyName(), ""));

        SingleMessageSentResponse response = defaultMessageService.sendOne(new SingleMessageSendingRequest(smsMessage));
        log.info("[StudyRegistrationSmsSender] sms is sent. response: {}",response.toString());
        return response;
    }

    private String createTextBody(String studyName, String studyUrl) {
        return new StringBuilder()
                .append(NEW_STUDY_SMS_PREFIX)
                .append(studyName.trim().substring(0, Math.min(studyName.length(), MAX_LENGTH)))
                .append(NEW_STUDY_SMS_SUFFIX)
                .append(NEW_LINE)
                .append(studyUrl)
                .toString();
    }
}
