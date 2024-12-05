package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.RegisterService;
import com.onb.Onboarding.domain.model.Register;
import com.onb.Onboarding.infrastructure.adapters.dto.RegisterDTO;
import com.onb.Onboarding.infrastructure.adapters.dto.RegisterDetailsDTO;
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
    public ResponseEntity<List<RegisterDTO>> getAllRegisters() {
        List<RegisterDTO> registerDTOs = registerService.getAllRegisters().stream().map(register -> {
            RegisterDTO dto = new RegisterDTO();
            dto.setId(register.getId());
            dto.setFullName(register.getUser().getName() + " " + register.getUser().getLastname());
            dto.setEmail(register.getUser().getEmail());
            dto.setCourseIds(register.getCourses().stream()
                    .map(courseGrade -> courseGrade.getCourse().getId())
                    .toList());
            dto.setCourseCount(register.getCourses().size());
            return dto;
        }).toList();

        return ResponseEntity.ok(registerDTOs);
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

    @GetMapping("/details-by-email/{email}")
    public ResponseEntity<List<RegisterDetailsDTO>> getRegisterDetailsByEmail(@PathVariable String email) {
        try {
            List<RegisterDetailsDTO> details = registerService.getRegisterDetailsByEmail(email);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }




    // Endpoint para actualizar la calificación de un curso en un registro
    @PutMapping("/update-grade/{registerId}/{courseId}")
    public ResponseEntity<Register> updateCourseGrade(
            @PathVariable String registerId,
            @PathVariable String courseId,
            @RequestParam int newGrade) {
        try {
            Register updatedRegister = registerService.updateCourseGrade(registerId, courseId, newGrade);
            return ResponseEntity.ok(updatedRegister);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}