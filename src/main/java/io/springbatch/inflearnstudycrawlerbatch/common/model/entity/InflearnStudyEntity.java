package io.springbatch.inflearnstudycrawlerbatch.common.model.entity;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.RecruitStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "study")
@AllArgsConstructor
@NoArgsConstructor
public class InflearnStudyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id", unique = true)
    private long id;

    @Column(name = "study_code", unique = true)
    private long studyCode;

    @Column(name = "study_name")
    private String name;

    @Column(name = "leader_name")
    private String leaderName;

    @Column(name = "recruit_status")
    @Enumerated(EnumType.STRING)
    private RecruitStatus recruitStatus;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
