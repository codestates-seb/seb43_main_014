package com.cv.domain.cv.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Link {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long linkId;

    @Enumerated(EnumType.STRING)
    private LinkName linkName;

    private String linkAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;

    public enum LinkName {
        LINK_GITHUB("깃허브 주소"),
        LINK_NOTION("노션 주소"),
        LINK_BLOG("블로그 주소");

        @Getter
        private String linkSource;

        LinkName(String linkSource) {
            this.linkSource = linkSource;
        }
    }
}
