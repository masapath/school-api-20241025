package com.school.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.api.entity.Student;
import com.school.api.repository.SchoolRepository;

@Service
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<Student> findStudentsByTeacher(int facilitatorId) {
        return schoolRepository.findStudentsByTeacher(facilitatorId);
    }
}
