package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.SchoolDto;

public interface SchoolService {

    SchoolDto save(SchoolDto entity);

    SchoolDto get(String id);

    void delete(String id);

    SchoolDto update(String id, SchoolDto entity);
}
