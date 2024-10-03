package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Exam;
import com.onb.Onboarding.infrastructure.adapters.ports.out.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    public ExamRepository examRepository;

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateExam(String id, Exam updatedExam) {
        Optional<Exam> existingExam = examRepository.findById(id);

        if (existingExam.isPresent()) {
            Exam exam = existingExam.get();
            exam.setQuestions(updatedExam.getQuestions());
            return examRepository.save(exam);
        } else {
            throw new RuntimeException("Exam not found with id " + id);
        }
    }

    public Exam getExamById(String id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            return exam.get();
        } else {
            throw new RuntimeException("Exam not found with id " + id);
        }
    }
}