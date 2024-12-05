package com.onb.Onboarding.infrastructure.adapters.dto;

import com.onb.Onboarding.domain.model.Course;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterDetailsDTO {
    private String registerId;
    private Course course;
    private int grade;
    private Date startDate;
}
