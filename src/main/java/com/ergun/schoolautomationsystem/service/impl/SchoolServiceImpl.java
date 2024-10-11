package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.ClassroomDto;
import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.dto.SchoolDto;
import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.entity.Lesson;
import com.ergun.schoolautomationsystem.entity.School;
import com.ergun.schoolautomationsystem.entity.Student;
import com.ergun.schoolautomationsystem.repository.SchoolRepository;
import com.ergun.schoolautomationsystem.service.ManagerService;
import com.ergun.schoolautomationsystem.service.SchoolService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private ClassroomServiceImpl classroomService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private LessonServiceImpl lessonService;

    @Override
    public SchoolDto save(SchoolDto entity) {
        return toDto(schoolRepository.save(toEntity(entity)));
    }

    public School toEntity(SchoolDto entity) {
        return School.builder()
                .id(entity.getId())
                .name(entity.getName())
                .manager(managerService.finByManagerId(entity.getManager().getId()))
                .classrooms(entity.getClassroomList().stream().map(classroom -> Classroom.builder()
                        .id(classroom.getId())
                        .name(classroom.getName())
                        .school(schoolRepository.findById(classroom.getSchoolId()).get())
                        .lessons(classroom.getLessons().stream().map(lesson -> Lesson.builder()
                                .id(lesson.getId())
                                .name(lesson.getName())
                                .classroom(classroomService.findClassroomById(lesson.getClassroomId()))
                                .teacher(teacherService.findTeacherById(lesson.getTeacher().getId()))
                                .studentList(lesson.getStudentList().stream().map(student -> Student.builder()
                                        .id(student.getId())
                                        .studentName(student.getName())
                                        .lesson(lessonService.findLessonById(student.getLessonId())).build()).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList())).build()).collect(Collectors.toList())).build();
    }

    public SchoolDto toDto(School school) {
        return SchoolDto.builder()
                .id(school.getId())
                .name(school.getName())
                .manager(managerService.get(String.valueOf(school.getManager().getId())))
                .classroomList(school.getClassrooms().stream().map(classroom -> ClassroomDto.builder()
                        .id(classroom.getId())
                        .name(classroom.getName())
                        .schoolId(classroom.getSchool().getId())
                        .lessons(classroom.getLessons().stream().map(lesson -> LessonDto.builder()
                                .id(lesson.getId())
                                .name(lesson.getName())
                                .classroomId(lesson.getClassroom().getId())
                                .teacher(teacherService.get(String.valueOf(lesson.getTeacher().getId())))
                                .studentList(lesson.getStudentList().stream().map(student -> StudentDto.builder()
                                        .id(student.getId())
                                        .name(student.getStudentName())
                                        .lessonId(student.getLesson().getId()).build()).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList())).build();
    }

    @Override
    public SchoolDto get(String id) {
        return toDto(schoolRepository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public void delete(String id) {
        schoolRepository.deleteById(Integer.parseInt(id));

    }

    @Override
    public SchoolDto update(String id, SchoolDto entity) {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(entity.getId());
        schoolDto.setName(entity.getName());
        schoolDto.setManager(entity.getManager());
        schoolDto.setClassroomList(entity.getClassroomList());
        return save(entity);

    }

    public School findBySchoolId(int schoolId) {
        return schoolRepository.findById(schoolId).get();
    }
}
