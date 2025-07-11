package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {
    public static void main(String[] args) {
        if (!DBConnection.ping()) {
            System.err.println("Cannot connect to DB");
            System.exit(1);
        }
        SpringApplication.run(StudentApplication.class, args);
    }
}