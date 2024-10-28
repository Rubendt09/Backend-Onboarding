package com.onb.Onboarding.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection =  "events")
public class Event {
    @Id private String id;
    @Getter @Setter String title;
    @Getter @Setter String message;
    @Getter @Setter private String urlImage;
    @Getter @Setter private Date startDate;
}