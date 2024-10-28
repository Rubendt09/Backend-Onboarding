package com.onb.Onboarding.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id private String id;

    @Getter @Setter String title;
    @Getter @Setter String message;
    @Getter @Setter private String urlImage;
    @Getter @Setter private Date shippDate;
    @Getter @Setter private boolean forAll;

    @Getter @Setter private List<String> userIds;
}
