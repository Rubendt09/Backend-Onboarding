package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Course;
import com.onb.Onboarding.domain.model.Register;
import com.onb.Onboarding.infrastructure.adapters.ports.out.CourseRepository;
import com.onb.Onboarding.infrastructure.adapters.ports.out.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Método para crear un registro nuevo
    public Register createRegister(Register register) {
        // Para cada curso en la lista de "courses", buscamos el curso por ID
        for (Register.CourseGrade courseGrade : register.getCourses()) {
            Optional<Course> courseOptional = courseRepository.findById(courseGrade.getCourse().getId());
            if (courseOptional.isPresent()) {
                courseGrade.setCourse(courseOptional.get());  // Asignamos el curso completo
            } else {
                throw new RuntimeException("Course not found with id " + courseGrade.getCourse().getId());
            }
        }
        return registerRepository.save(register);
    }

    // Método para obtener un registro por ID del usuario
    public List<Register.CourseGrade> getRegisterByUserId(String userId) {
        Optional<Register> registerOptional = registerRepository.findByUserId(userId);
        if (registerOptional.isPresent()) {
            Register register = registerOptional.get();
            return register.getCourses();
        } else {
            throw new RuntimeException("No register found for userId " + userId);
        }
    }
}