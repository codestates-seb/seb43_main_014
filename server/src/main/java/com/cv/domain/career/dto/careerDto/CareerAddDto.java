package com.cv.domain.career.dto.careerDto;

import com.cv.domain.career.dto.careerSkillStackDto.CareerSkillStackAddDto;
import lombok.Data;

import java.util.List;

@Data
public class CareerAddDto {
    private String joinYear;

    private String joinMonth;

    private String retirementYear;

    private String retirementMonth;

    private String companyName;

    private String duty;

    private String developmentJob;

    private String description;

    private List<CareerSkillStackAddDto> careerSkillStacks;
}
