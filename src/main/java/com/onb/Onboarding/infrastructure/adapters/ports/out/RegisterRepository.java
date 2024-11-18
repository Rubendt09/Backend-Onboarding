package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegisterRepository extends MongoRepository<Register, String> {
    Optional<Register> findByUserId(String userId);
}
