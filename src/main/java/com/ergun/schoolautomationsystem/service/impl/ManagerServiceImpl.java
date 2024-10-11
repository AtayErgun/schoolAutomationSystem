package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.ManagerDto;
import com.ergun.schoolautomationsystem.entity.Manager;
import com.ergun.schoolautomationsystem.repository.ManagerRepository;
import com.ergun.schoolautomationsystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository repository;
    @Override
    public ManagerDto create(ManagerDto entity) {
        return toDto(repository.save(toEntity(entity)));
    }

    private Manager toEntity(ManagerDto entity) {
        Manager manager=new Manager();
        manager.setId(entity.getId());
        manager.setName(entity.getName());
        return manager;
    }

    public ManagerDto toDto(Manager manager) {
        ManagerDto managerDto=new ManagerDto();
        managerDto.setName(managerDto.getName());
        managerDto.setId(managerDto.getId());
        return managerDto;
    }

    @Override
    public ManagerDto get(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public ManagerDto update(String id, ManagerDto entity) {
       ManagerDto managerDto=toDto(repository.findById(Integer.parseInt(id)).get());
       managerDto.setId(entity.getId());
       managerDto.setName(entity.getName());
       return toDto(repository.save(toEntity(managerDto)));
    }

    public Manager finByManagerId(int id) {
        return repository.findById(id).get();
    }
}
