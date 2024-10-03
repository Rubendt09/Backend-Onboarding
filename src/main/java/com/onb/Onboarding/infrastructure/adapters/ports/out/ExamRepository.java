package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {

}
