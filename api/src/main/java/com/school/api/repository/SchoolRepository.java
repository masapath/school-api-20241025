package com.school.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.school.api.entity.Student;
import com.school.api.entity.FindStudentsByTeacherParams;

@Mapper
public interface SchoolRepository {
    
    List<Student> findStudentsByTeacher(FindStudentsByTeacherParams params);
}
