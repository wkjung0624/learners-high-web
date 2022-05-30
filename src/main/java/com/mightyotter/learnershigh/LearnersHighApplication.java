package com.mightyotter.learnershigh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LearnersHighApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnersHighApplication.class, args);
	}

}
