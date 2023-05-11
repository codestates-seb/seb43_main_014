package com.cv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SlaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlaveApplication.class, args);
	}

}
