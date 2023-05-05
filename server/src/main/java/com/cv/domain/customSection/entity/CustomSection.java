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

    @ManyToOne
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}
