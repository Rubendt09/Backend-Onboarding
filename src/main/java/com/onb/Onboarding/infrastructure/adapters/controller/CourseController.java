package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.CourseService;
import com.onb.Onboarding.domain.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint para crear un nuevo curso (creará un examen vacío si no se provee uno)
    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourse();
        return new ResponseEntity<>(courses, HttpStatus.OK); // Retorna 200 OK con la lista de Cursos
    }

    // Endpoint para obtener un curso por ID
    @GetMapping("/get/{id}")
    public Optional<Course> getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    // Endpoint para actualizar un curso
    @PutMapping("/update/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id){
        try {
            courseService.deleteCourseById(id);
            return new ResponseEntity<>("Curso y examen asociados eliminados correctamente.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}