package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.TeacherDto;
import com.ergun.schoolautomationsystem.entity.Teacher;
import com.ergun.schoolautomationsystem.repository.TeacherRepository;
import com.ergun.schoolautomationsystem.request.TeacherRequest;
import com.ergun.schoolautomationsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public TeacherDto update(String id, TeacherDto entity) {
        TeacherDto dto=toDto(teacherRepository.findById(Integer.parseInt(id)).get());
        dto.setId(entity.getId());
        dto.setTeacherName(entity.getTeacherName());
        return toDto(teacherRepository.save(toEntity(dto)));
    }

    private Teacher toEntity(TeacherDto dto) {
        Teacher teacher=new Teacher();
        teacher.setId(dto.getId());
        teacher.setTeacherName(dto.getTeacherName());
        return teacher;
    }

    private TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto=new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setTeacherName(teacher.getTeacherName());
        return teacherDto;
    }
    public Teacher findTeacherById(int id){
        return teacherRepository.findById(id).get();
    }

    @Override
    public TeacherDto create(TeacherDto teacherDto) {
        return toDto(teacherRepository.save(toEntity(teacherDto)));
    }

    @Override
    public TeacherDto get(String id) {
       return toDto(findTeacherById(Integer.parseInt(id)));
    }

    @Override
    public void delete(String id) {
        teacherRepository.deleteById(Integer.parseInt(id));
    }
}
