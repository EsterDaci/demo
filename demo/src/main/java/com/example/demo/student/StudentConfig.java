package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
          Student ester =  new Student(
                    "Ester",
                    "de@gmail.com",
                    LocalDate.of(1994, Month.DECEMBER,31)
            );
            Student alex =  new Student(
                    "Alex",
                    "a@gmail.com",
                    LocalDate.of(1993, Month.JANUARY,31)
            );
            repository.saveAll(
                    List.of(ester,alex)
            );

        };
    }
}
