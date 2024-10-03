package com.onb.Onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan("com.onb.Onboarding.model")
public class OnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}

}