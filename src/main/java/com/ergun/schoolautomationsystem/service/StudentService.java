package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.dto.TeacherDto;

public interface StudentService {
    StudentDto create(StudentDto entity);

    StudentDto get(String id);

    void delete(String id);

    StudentDto update(String id, StudentDto entity);
}
