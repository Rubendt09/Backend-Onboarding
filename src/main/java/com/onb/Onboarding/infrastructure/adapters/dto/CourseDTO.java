package com.onb.Onboarding.infrastructure.adapters.dto;

import com.onb.Onboarding.domain.model.Course;
import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {
    private Course course;
    private int grade;
    private Date startDate;
}