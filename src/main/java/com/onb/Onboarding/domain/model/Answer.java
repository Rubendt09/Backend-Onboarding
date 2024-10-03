package com.onb.Onboarding.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Answer {

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
}