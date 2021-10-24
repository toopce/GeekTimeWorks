package com.example.autoassemble.configuration;

import com.example.autoassemble.entity.StudentInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {


    @Bean
    public StudentInfo studentInfo(){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(1);
        studentInfo.setName("wmj");
        return studentInfo;
    }
}
