package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.ClassroomDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.entity.Teacher;

public interface ClassroomService {

    ClassroomDto get(String s);

    ClassroomDto create(ClassroomDto entity);

    void delete(String id);

    ClassroomDto update(String id, ClassroomDto entity);
}
