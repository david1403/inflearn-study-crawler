package io.springbatch.inflearnstudycrawlerbatch.common.model.repository;

import io.springbatch.inflearnstudycrawlerbatch.common.model.entity.InflearnStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InflearnStudyRepository extends JpaRepository<InflearnStudyEntity, Long> {

    boolean existsByStudyCode(long studyCode);
}
