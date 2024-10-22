package com.school.api.entity;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String loginId;
    private Classroom classroom;
}
