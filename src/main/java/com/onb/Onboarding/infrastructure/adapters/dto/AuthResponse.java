package com.onb.Onboarding.infrastructure.adapters.dto;

import lombok.Getter;
import lombok.Setter;
import com.onb.Onboarding.domain.model.User;

public class AuthResponse {
    @Getter @Setter private User user;
    @Getter @Setter private String token;

    public AuthResponse(User user, String token){
        this.user = user;
        this.token = token;
    }
}
