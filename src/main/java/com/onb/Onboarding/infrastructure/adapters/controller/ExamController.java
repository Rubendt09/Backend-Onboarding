package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.ExamService;
import com.onb.Onboarding.domain.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @PutMapping("/update/{id}")
    public Exam updateExam(@PathVariable String id, @RequestBody Exam exam) {
        return examService.updateExam(id, exam);
    }

    @GetMapping("/get/{id}")
    public Exam getExamById(@PathVariable String id) {
        return examService.getExamById(id);
    }
}