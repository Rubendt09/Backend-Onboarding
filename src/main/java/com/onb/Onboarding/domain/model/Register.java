package com.onb.Onboarding.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "register")
public class Register {

    @Id private String id;
    @DBRef private User user;
    private List<CourseGrade> courses;

    @Data
    public static class CourseGrade {
        @DBRef private Course course;
        private int grade;
    }
}