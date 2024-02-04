package io.springbatch.inflearnstudycrawlerbatch.common.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InflearnStudyDto {
    private long studyCode;
    private String studyName;
    private String leaderName;
    private RecruitStatus recruitStatus;
    private int likeCount;
    private int viewCount;
    private int commentCount;

    @Setter
    private LocalDateTime updatedAt;
}
