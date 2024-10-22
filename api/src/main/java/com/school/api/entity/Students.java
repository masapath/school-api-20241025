package com.school.api.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Students {
    private List<Student> students;
    private int totalCount;
}
