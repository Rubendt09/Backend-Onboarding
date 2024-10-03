package com.onb.Onboarding.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {
    @Id private String id;
    @Getter @Setter String name;
    @Getter @Setter private String lastname;
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private String rol;
}
