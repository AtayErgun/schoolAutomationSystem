package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.LessonDto;

public interface LessonService {
    LessonDto crate(LessonDto entity);

    LessonDto get(String id);

    void delete(String id);

    LessonDto update(String id, LessonDto entity);
}
