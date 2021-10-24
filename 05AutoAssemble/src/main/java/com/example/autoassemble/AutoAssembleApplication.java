package com.example.autoassemble;

import com.example.autoassemble.entity.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoAssembleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoAssembleApplication.class, args);
    }

    @Autowired
    StudentInfo studentInfo;



}
