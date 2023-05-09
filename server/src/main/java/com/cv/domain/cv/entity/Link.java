package com.cv.domain.cv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Link {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long linkId;

    private String linkName;

    private String linkAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}
