package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Course;
import com.onb.Onboarding.domain.model.Exam;
import com.onb.Onboarding.infrastructure.adapters.ports.out.CourseRepository;
import com.onb.Onboarding.infrastructure.adapters.ports.out.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    public Course createCourse(Course course) {
        if (course.getExam() == null) {
            Exam emptyExam = new Exam();
            emptyExam.setQuestions(null);
            Exam savedExam = examRepository.save(emptyExam);

            course.setExam(savedExam);
        }

        return courseRepository.save(course);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public Course updateCourse(String id, Course updatedCourse) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setName(updatedCourse.getName());
            course.setDescripcion(updatedCourse.getDescripcion());
            course.setUrlVideo(updatedCourse.getUrlVideo());

            if (updatedCourse.getExam() != null && updatedCourse.getExam().getId() != null) {
                Optional<Exam> examOptional = examRepository.findById(updatedCourse.getExam().getId());
                examOptional.ifPresent(course::setExam);
            }

            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found with id " + id);
        }
    }
}