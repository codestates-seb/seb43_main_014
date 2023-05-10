package com.cv.domain.career.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class CareerDto {

    @Data
    public static class Add {
        private String joinYear;

        private String joinMonth;

        private String retirementYear;

        private String retirementMonth;

        private String companyName;

        private String duty;

        private String developmentJob;

        private List<CareerSkillStackDto.Add> careerSkillStacks;
    }

    @Data
    public static class Response {
        private long careerId;

        private String companyName;

        private String duty;

        private String developmentJob;

        private List<CareerSkillStackDto.Add> careerSkillStacks;
    }
}
