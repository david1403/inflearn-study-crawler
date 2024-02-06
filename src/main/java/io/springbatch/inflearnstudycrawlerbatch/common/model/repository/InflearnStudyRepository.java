package io.springbatch.inflearnstudycrawlerbatch.common.model.repository;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.RecruitStatus;
import io.springbatch.inflearnstudycrawlerbatch.common.model.entity.InflearnStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InflearnStudyRepository extends JpaRepository<InflearnStudyEntity, Long> {

    Optional<InflearnStudyEntity> findByStudyCode(long studyCode);


    @Modifying
    @Query("UPDATE InflearnStudyEntity s SET " +
            "s.commentCount = :commentCount," +
            "s.likeCount = :likeCount," +
            "s.recruitStatus = :recruitStatus," +
            "s.viewCount = :viewCount " +
            "where s.studyCode = :studyCode")
    void updateByStudyCode(long studyCode, int likeCount, int commentCount, int viewCount, RecruitStatus recruitStatus);
}
