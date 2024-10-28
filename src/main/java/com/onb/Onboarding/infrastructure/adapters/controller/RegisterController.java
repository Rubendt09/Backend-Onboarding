package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.RegisterService;
import com.onb.Onboarding.domain.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/all")
    public ResponseEntity<List<Register>> getAllRegisters() {
        List<Register> registers = registerService.getAllRegisters();
        return ResponseEntity.ok(registers);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Register> createRegister(
            @PathVariable String userId,
            @RequestBody List<String> courseIds
    ) {
        try {
            Register register = registerService.createRegister(userId, courseIds);
            return ResponseEntity.ok(register);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/add-courses/{registerId}")
    public ResponseEntity<Register> addCoursesToRegister(
            @PathVariable String registerId,
            @RequestBody List<String> courseIds
    ) {
        try {
            Register updatedRegister = registerService.addCoursesToRegister(registerId, courseIds);
            return ResponseEntity.ok(updatedRegister);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/remove-courses/{registerId}")
    public ResponseEntity<Register> removeCoursesFromRegister(
            @PathVariable String registerId,
            @RequestBody List<String> courseIds
    ) {
        try {
            Register updatedRegister = registerService.removeCoursesFromRegister(registerId, courseIds);
            return ResponseEntity.ok(updatedRegister);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}