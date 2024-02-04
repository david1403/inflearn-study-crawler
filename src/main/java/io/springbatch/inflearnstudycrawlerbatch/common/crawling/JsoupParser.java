package io.springbatch.inflearnstudycrawlerbatch.common.crawling;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsoupParser {
    public Elements getElements(String url, String selector) throws IOException {
        return getDocument(url).select(selector);
    }

    private Document getDocument(String url) throws IOException {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("[InflearnCrawler] cannot create connection from url: {}", url, e);
            throw e;
        }
    }
}
