package com.onb.Onboarding.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class CourseRegisterDTO {

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    String name;

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    private String description;

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    private String urlVideo;

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    private String exam;

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    private String estatus;
}