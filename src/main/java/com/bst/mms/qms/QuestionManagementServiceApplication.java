package com.bst.mms.qms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuestionManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionManagementServiceApplication.class, args);
		System.out.println("Tomcat Running For Question Management Service...");
	}
}
