package io.springbatch.inflearnstudycrawlerbatch.common.model.converter;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import io.springbatch.inflearnstudycrawlerbatch.common.model.entity.InflearnStudyEntity;
import org.springframework.stereotype.Component;

@Component
public class InflearnStudyConverter {

    public InflearnStudyEntity convertToInflearnStudyEntity(InflearnStudyDto inflearnStudyDto) {
        if (inflearnStudyDto == null) {
            return null;
        }

        InflearnStudyEntity entity = new InflearnStudyEntity();
        entity.setStudyCode(inflearnStudyDto.getStudyCode());
        entity.setName(inflearnStudyDto.getStudyName());
        entity.setLeaderName(inflearnStudyDto.getLeaderName());
        entity.setRecruitStatus(inflearnStudyDto.getRecruitStatus());
        entity.setLikeCount(inflearnStudyDto.getLikeCount());
        entity.setViewCount(inflearnStudyDto.getViewCount());
        entity.setCommentCount(inflearnStudyDto.getCommentCount());
        entity.setUpdatedAt(inflearnStudyDto.getUpdatedAt());

        return entity;
    }

    public InflearnStudyDto convertToInflearnStudyDto(InflearnStudyEntity entity) {
        if (entity == null) {
            return null;
        }

        return InflearnStudyDto.builder()
                .studyCode(entity.getStudyCode())
                .studyName(entity.getName())
                .leaderName(entity.getLeaderName())
                .recruitStatus(entity.getRecruitStatus())
                .likeCount(entity.getLikeCount())
                .viewCount(entity.getViewCount())
                .commentCount(entity.getCommentCount())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
