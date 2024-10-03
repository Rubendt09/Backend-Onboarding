package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}