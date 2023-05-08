package com.cv.domain.career.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class CareerDto {

    @Data
    public static class Add {
        private String companyName;

        private String duty;

        private String companyInformation;

        private String developmentJob;

        private List<CareerSkillStackDto.Add> careerSkillStacks;
    }

    @Getter
    public static class Response {
        private long careerId;

        private String companyName;

        private String duty;

        private String companyInformation;

        private String developmentJob;

        private List<CareerSkillStackDto.Add> careerSkillStacks;
    }
}
