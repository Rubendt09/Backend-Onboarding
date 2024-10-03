package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
