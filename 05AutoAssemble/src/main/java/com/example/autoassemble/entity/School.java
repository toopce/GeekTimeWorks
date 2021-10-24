package com.example.autoassemble.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class School implements ISchool{


    @Autowired
    Klass klass;

    @Resource(name = "studentInfo")
    StudentInfo studentInfo;


    @Override
    public void ding() {
        System.out.println("dingdingding");
        System.out.println(klass.toString());
    }


}
