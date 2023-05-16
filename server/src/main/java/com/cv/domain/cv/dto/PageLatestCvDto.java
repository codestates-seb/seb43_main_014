package com.cv.domain.cv.dto;

import com.cv.domain.cv.entity.Cv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class PageLatestCvDto {
    protected List<LatestCv> latestCvs;
    private int totalPages;
    private long totalElements;


    public PageLatestCvDto(Page<Cv> cvPage) {
        this.latestCvs = cvPage.getContent().stream()
                .map(cv -> new LatestCv(cv.getTitle(), cv.getCreatedAt(), cv.getDevelopmentJob()))
                .collect(Collectors.toList());
        this.totalPages = cvPage.getTotalPages();
        this.totalElements = cvPage.getTotalElements();
    }

    @Data
    private static class LatestCv {
        private String title;
        private LocalDateTime createdAt;
        private String developmentJob;
    }
}
