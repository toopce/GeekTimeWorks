package com.example.autoassemble.entity;

import org.springframework.stereotype.Service;

public class StudentInfo {
    private Integer id;
    private String name;

    public StudentInfo(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public StudentInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
