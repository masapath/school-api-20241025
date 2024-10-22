package com.school.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.school.api.entity.Student;

@Mapper
public interface SchoolRepository {
    
    List<Student> findStudentsByTeacher(int facilitatorId);
}
