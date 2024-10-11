package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.dto.TeacherDto;
import com.ergun.schoolautomationsystem.entity.Lesson;
import com.ergun.schoolautomationsystem.entity.Student;
import com.ergun.schoolautomationsystem.repository.SchoolRepository;
import com.ergun.schoolautomationsystem.repository.StudentRepository;
import com.ergun.schoolautomationsystem.service.LessonService;
import com.ergun.schoolautomationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private LessonServiceImpl service;
    @Override
    public StudentDto create(StudentDto entity) {
        return toDto(repository.save(toEntity(entity)));
    }

    @Override
    public StudentDto get(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public StudentDto update(String id, StudentDto entity) {
        StudentDto studentDto=toDto(repository.findById(Integer.parseInt(id)).get());
        studentDto.setId(entity.getId());
        studentDto.setName(entity.getName());
        return toDto(repository.save(toEntity(studentDto)));
    }

    private Student toEntity(StudentDto studentDto) {
        Student student=new Student();
        student.setId(studentDto.getId());
        student.setStudentName(studentDto.getName());
        student.setLesson(service.findLessonById(studentDto.getLessonId()));

       // Lesson lesson=service.get(String.valueOf(studentDto.getLesson().id));
//       // User user=userService.getUserEntity(String.valueOf(dto.getUser().id));
//        student.setLesson(lesson);
        return student;
    }

    public StudentDto toDto(Student student){
        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getStudentName());
        studentDto.setLessonId(student.getLesson().getId());
        return studentDto;
    }
}
