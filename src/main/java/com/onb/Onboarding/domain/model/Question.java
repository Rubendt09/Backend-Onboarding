package com.onb.Onboarding.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Question {

    private String question;
    private Answer answer;
}
