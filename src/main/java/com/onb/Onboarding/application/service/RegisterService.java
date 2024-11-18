package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Course;
import com.onb.Onboarding.domain.model.Register;
import com.onb.Onboarding.domain.model.User;
import com.onb.Onboarding.infrastructure.adapters.ports.out.CourseRepository;
import com.onb.Onboarding.infrastructure.adapters.ports.out.RegisterRepository;
import com.onb.Onboarding.infrastructure.adapters.ports.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Register> getAllRegisters() {
        return registerRepository.findAll();
    }

    public Register createRegister(String userId, List<String> courseIds) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }

        List<Course> courses = courseRepository.findAllById(courseIds);
        if (courses.isEmpty()) {
            throw new Exception("Courses not found");
        }

        Register register = new Register();
        register.setUser(user.get());
        register.setCourses(
                courses.stream().map(course -> {
                    Register.CourseGrade courseGrade = new Register.CourseGrade();
                    courseGrade.setCourse(course);
                    courseGrade.setGrade(0); // Por defecto o inicializable luego
                    courseGrade.setStartDate(null);
                    return courseGrade;
                }).collect(Collectors.toList())
        );

        return registerRepository.save(register);
    }

    // Método para agregar cursos a un registro existente
    public Register addCoursesToRegister(String registerId, List<String> courseIds) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(registerId);
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register not found");
        }

        Register register = optionalRegister.get();
        List<Course> coursesToAdd = courseRepository.findAllById(courseIds);

        // Filtrar cursos para evitar duplicados en la lista
        List<String> existingCourseIds = register.getCourses().stream()
                .map(courseGrade -> courseGrade.getCourse().getId())
                .collect(Collectors.toList());

        List<Register.CourseGrade> newCourses = coursesToAdd.stream()
                .filter(course -> !existingCourseIds.contains(course.getId()))
                .map(course -> {
                    Register.CourseGrade courseGrade = new Register.CourseGrade();
                    courseGrade.setCourse(course);
                    courseGrade.setGrade(0); // Valor inicial
                    courseGrade.setStartDate(null); // Fecha de inicio en null
                    return courseGrade;
                }).collect(Collectors.toList());

        // Agregar los nuevos cursos a la lista existente
        register.getCourses().addAll(newCourses);
        return registerRepository.save(register);
    }

    // Método para eliminar cursos de un registro existente
    public Register removeCoursesFromRegister(String registerId, List<String> courseIds) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(registerId);
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register not found");
        }

        Register register = optionalRegister.get();

        // Filtrar la lista de cursos para excluir los que se deben eliminar
        register.setCourses(
                register.getCourses().stream()
                        .filter(courseGrade -> !courseIds.contains(courseGrade.getCourse().getId()))
                        .collect(Collectors.toList())
        );

        return registerRepository.save(register);
    }

    public List<Register.CourseGrade> getRegisterDetailsByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found with email: " + email);
        }

        Optional<Register> registerOptional = registerRepository.findByUserId(user.getId());
        if (registerOptional.isEmpty()) {
            throw new Exception("No register found for user with email: " + email);
        }

        Register register = registerOptional.get();
        return register.getCourses();
    }


}