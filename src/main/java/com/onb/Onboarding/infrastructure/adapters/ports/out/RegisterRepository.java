package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository<Register, String> {
}
