package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.RegisterService;
import com.onb.Onboarding.domain.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    // Endpoint para crear un nuevo registro
    @PostMapping("/create")
    public Register createRegister(@RequestBody Register register) {
        return registerService.createRegister(register);
    }

    // Endpoint para obtener los registros de un usuario por su ID
    @GetMapping("/getByUser/{userId}")
    public List<Register.CourseGrade> getRegisterByUserId(@PathVariable String userId) {
        return registerService.getRegisterByUserId(userId);
    }
}