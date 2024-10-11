package com.ergun.schoolautomationsystem.service;

import com.ergun.schoolautomationsystem.dto.ManagerDto;

public interface ManagerService {
    ManagerDto create(ManagerDto entity);

    ManagerDto get(String id);

    void delete(String id);

    ManagerDto update(String id, ManagerDto entity);
}
