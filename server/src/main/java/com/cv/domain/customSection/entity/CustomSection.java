package com.cv.domain.customSection.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
    @JoinColumn(name = "CV_ID", nullable = false)
    private Cv cv;
}
