package com.onb.Onboarding.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "courses")
public class Course {

    @Id
    private String id;
    private String name;
    private String descripcion;
    private String urlVideo;

    @DBRef
    private Exam exam;
}