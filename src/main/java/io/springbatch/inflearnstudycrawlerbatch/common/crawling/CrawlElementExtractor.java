package io.springbatch.inflearnstudycrawlerbatch.common.crawling;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.RecruitStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrawlElementExtractor {
    private final String CODE_ATTRIBUTE = "href";
    private final String CODE_SPLITTER = "/";
    private final String NAME_TAG_CLASS = "title__text";
    private final String LEADER_NAME_CLASS = "question__info-user-name";
    private final String RECRUIT_STATUS_CLASS = "title-sub__text";
    private final String LIKE_COMMENT_COUNT_CLASS = "comment__count";
    private final String VIEW_COUNT_CLASS = "view__count";
    private final int FIRST_ITEM_INDEX = 0;
    private final int SECOND_ITEM_INDEX = 1;
    private final int THIRD_ITEM_INDEX = 2;


    public InflearnStudyDto extractInflearnStudyDto(Element element) {
        long id = this.extractCode(element);
        String name = this.extractName(element);
        String leaderName = this.extractLeaderName(element);
        RecruitStatus recruitStatus = this.extractRecruitStatus(element);
        int likeCount = this.extractLikeCount(element);
        int viewCount = this.extractViewCount(element);
        int commentCount = this.extractCommentCount(element);

        InflearnStudyDto inflearnStudyDto = InflearnStudyDto.builder()
                .studyCode(id)
                .studyName(name)
                .leaderName(leaderName)
                .recruitStatus(recruitStatus)
                .likeCount(likeCount)
                .viewCount(viewCount)
                .commentCount(commentCount)
                .build();

        return inflearnStudyDto;
    }

    private long extractCode(Element element) {
        return Long.parseLong(element.childNodes()
                .get(SECOND_ITEM_INDEX)
                .attributes().get(CODE_ATTRIBUTE)
                .split(CODE_SPLITTER)[THIRD_ITEM_INDEX]);
    }

    private String extractName(Element element) {
        return element.getElementsByClass(NAME_TAG_CLASS).get(FIRST_ITEM_INDEX).text();
    }

    private String extractLeaderName(Element element) {
        return element.getElementsByClass(LEADER_NAME_CLASS)
                .get(FIRST_ITEM_INDEX).text();
    }
    private RecruitStatus extractRecruitStatus(Element element) {
        return RecruitStatus.getRecruitStatusByDescription(element.getElementsByClass(RECRUIT_STATUS_CLASS)
                        .get(FIRST_ITEM_INDEX).text());
    }

    private int extractLikeCount(Element element) {
        return Integer.parseInt(element.getElementsByClass(LIKE_COMMENT_COUNT_CLASS)
                .get(FIRST_ITEM_INDEX).text());
    }
    private int extractViewCount(Element element) {
        return Integer.parseInt(element.getElementsByClass(VIEW_COUNT_CLASS)
                .get(FIRST_ITEM_INDEX).text());
    }
    private int extractCommentCount(Element element) {
        return Integer.parseInt(element.getElementsByClass(LIKE_COMMENT_COUNT_CLASS)
                .get(SECOND_ITEM_INDEX).text());
    }
}
