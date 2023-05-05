package com.cv.domain.cv.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Cv {

    //    TODO : profileImage 추가
    //    TODO : myLinks 추가
    //    TODO : portfolio 추가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvId;

    private String email;

    private String userName;

    private String address;

    private String phone;

    private String selfIntroduction;

    private String developmentJob;

    private String birthYear;

    private String birthMonth;

    private String birthDay;

    private Boolean isDelete = false;
}
