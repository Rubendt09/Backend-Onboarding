package com.onb.Onboarding.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class UserRegisterDTO {

    @Getter @Setter
    @NotEmpty(message =  "Name is requiered")
    private String name;

    @Getter @Setter
    @NotEmpty(message =  "Last name is requiered")
    private String lastname;

    @Getter @Setter
    @NotEmpty(message =  "Email is requiered")
    private String email;

    @Getter @Setter
    @NotEmpty(message =  "Password is requiered")
    private String password;

    @Getter @Setter
    @NotEmpty(message =  "Rol is requiered")
    private String rol;
}