package com.example;

import com.example.controller.NoteController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class Hw16SpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw16SpringDataApplication.class, args);
	}

}
