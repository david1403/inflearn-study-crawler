package io.springbatch.inflearnstudycrawlerbatch.common.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@RequiredArgsConstructor
public enum RecruitStatus {
    RECRUITING("모집중"),
    RECRUITED("모집완료");

    private final String description;
    private static final Map<String, RecruitStatus> RECRUIT_STATUS_MAP;

    static {
        Map<String, RecruitStatus> map = new ConcurrentHashMap<>();
        for (RecruitStatus recruitStatus : RecruitStatus.values()) {
            map.put(recruitStatus.getDescription(), recruitStatus);
        }
        RECRUIT_STATUS_MAP = Collections.unmodifiableMap(map);
    }

    public static RecruitStatus getRecruitStatusByDescription(String description) {
        return RECRUIT_STATUS_MAP.getOrDefault(description, RECRUITING);
    }
}
