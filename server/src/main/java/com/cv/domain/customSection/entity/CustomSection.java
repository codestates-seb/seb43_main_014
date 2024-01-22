package com.cv.domain.customSection.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomSection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long customSectionId;

    private String customName;

    @Lob
    private String customContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}
