package com.cv.domain.education.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Education {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long educationId;

    private String admissionYear;

    private String admissionMonth;

    private String graduationYear;

    private String graduationMonth;

    private String schoolName;

    private String major;

    private String degree;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "CV_ID", nullable = false)
    private Cv cv;
}
