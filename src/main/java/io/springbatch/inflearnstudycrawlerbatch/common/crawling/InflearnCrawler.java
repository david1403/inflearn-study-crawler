package io.springbatch.inflearnstudycrawlerbatch.common.crawling;

import io.springbatch.inflearnstudycrawlerbatch.common.model.dto.InflearnStudyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class InflearnCrawler {
    private final JsoupParser jsoupParser;
    private final CrawlElementExtractor crawlElementExtractor;

    private final String STUDY_PAGE_URL = "https://www.inflearn.com/community/studies";
    private final String TEAM_PROJECT_PAGE_URL = "https://www.inflearn.com/community/projects";
    private final String STUDY_LIST_SELECTOR = ".question-container";


    public List<InflearnStudyDto> getStudies() throws IOException {
        Elements elements = new Elements();
        try {
            elements = jsoupParser.getElements(STUDY_PAGE_URL, STUDY_LIST_SELECTOR);
        } catch (IOException ioe) {
            log.error("[InflearnCrawler] cannot open study page.", ioe);
            return new ArrayList<>();
        }

        return elements.stream()
                .map(crawlElementExtractor::extractInflearnStudyDto)
                .collect(Collectors.toList());
    }
}
