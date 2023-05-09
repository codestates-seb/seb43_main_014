package com.cv.domain.cv.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Portfolio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long portfolioId;

    private String portfolioAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}
