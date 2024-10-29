package com.onb.Onboarding.infrastructure.adapters.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDTO {
    private String id;
    private String fullName;
    private String email;
    private List<String> courseIds;
    private int courseCount;
}
