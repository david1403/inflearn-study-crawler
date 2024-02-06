package io.springbatch.inflearnstudycrawlerbatch.common.model.service;

import io.springbatch.inflearnstudycrawlerbatch.common.model.converter.InflearnStudyConverter;
import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import io.springbatch.inflearnstudycrawlerbatch.common.model.entity.InflearnStudyEntity;
import io.springbatch.inflearnstudycrawlerbatch.common.model.repository.InflearnStudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InflearnStudyService {
    private final InflearnStudyRepository inflearnStudyRepository;
    private final InflearnStudyConverter inflearnStudyConverter;

    public boolean checkExist(InflearnStudyDto inflearnStudyDto) {
        return inflearnStudyRepository.findByStudyCode(inflearnStudyDto.getStudyCode()).isPresent();
    }

    public int updateStudyInfo(InflearnStudyDto inflearnStudyDto) {
        inflearnStudyRepository.updateByStudyCode(
                inflearnStudyDto.getStudyCode(),
                inflearnStudyDto.getLikeCount(),
                inflearnStudyDto.getCommentCount(),
                inflearnStudyDto.getViewCount(),
                inflearnStudyDto.getRecruitStatus()
        );
        return 0;
    }

    public void insertStudy(InflearnStudyDto inflearnStudyDto) {
        inflearnStudyRepository.save(inflearnStudyConverter.convertToInflearnStudyEntity(inflearnStudyDto));
    }
}
