package com.onb.Onboarding.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "exams")
public class Exam {

    @Id
    private String id;
    private List<Question> questions;
}
