package com.example.autoassemble.entity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Klass {
    private List<StudentInfo> StudentInfos;

    public Klass(List<StudentInfo> studentInfos) {
        StudentInfos = studentInfos;
        System.out.println(studentInfos);
    }


    public List<StudentInfo> getStudentInfos() {
        return StudentInfos;
    }

    public void setStudentInfos(List<StudentInfo> studentInfos) {
        StudentInfos = studentInfos;
    }

    @Override
    public String toString() {
        return "Klass{" +
                "StudentInfos=" + StudentInfos +
                '}';
    }
}
