package com.graduationproject.studentinformationsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
@SpringBootApplication
public class StudentInformationSystemApplication implements ApplicationListener<ApplicationReadyEvent> {
	public static void main(String[] args) {
		SpringApplication.run(StudentInformationSystemApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		log.info("STUDENT INFORMATION SYSTEM STARTED!");
	}
}
