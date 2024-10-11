package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.TeacherDto;
import com.ergun.schoolautomationsystem.request.TeacherRequest;

public interface TeacherService {
    TeacherDto update(String id, TeacherDto entity);

    TeacherDto create(TeacherDto teacherDto);

    TeacherDto get(String id);

    void delete(String id);
}
