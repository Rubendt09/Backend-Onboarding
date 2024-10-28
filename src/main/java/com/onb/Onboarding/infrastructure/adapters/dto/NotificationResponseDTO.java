package com.onb.Onboarding.infrastructure.adapters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class NotificationResponseDTO {
    private String title;
    private String message;
    private String urlImage;
    private Date shippDate;

    // Constructor
    public NotificationResponseDTO (String title, String message, String urlImage, Date shippDate) {
        this.title = title;
        this.message = message;
        this.urlImage = urlImage;
        this.shippDate = shippDate;
    }
}
