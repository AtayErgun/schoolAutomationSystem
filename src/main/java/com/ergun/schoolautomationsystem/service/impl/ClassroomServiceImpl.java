package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.ClassroomDto;
import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.entity.Lesson;
import com.ergun.schoolautomationsystem.entity.Student;
import com.ergun.schoolautomationsystem.repository.ClassroomRepository;
import com.ergun.schoolautomationsystem.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final TeacherServiceImpl teacherService;

    private final LessonServiceImpl lessonService;

    private final SchoolServiceImpl schoolService;


    @Override
    public ClassroomDto get(String s) {
        return toDto(classroomRepository.findById(Integer.parseInt(s)).get());
    }

    public Classroom findClassroomById(int id) {
        return classroomRepository.findById(id).get();
    }

    @Override
    public ClassroomDto create(ClassroomDto entity) {
        return toDto(classroomRepository.save(toEntity(entity)));
    }

    private ClassroomDto toDto(Classroom classroom) {
        return ClassroomDto.builder()
                .name(classroom.getName())
                .id(classroom.getId())
                .schoolId(classroom.getSchool().getId())
                .lessons(classroom.getLessons().stream()
                        .map(lesson -> LessonDto.builder()
                                .id(lesson.getId())
                                .name(lesson.getName())
                                .classroomId(lesson.getClassroom().getId())
                                .teacher(teacherService.get(String.valueOf(lesson.getTeacher().getId())))
                                .studentList(lesson.getStudentList().stream()
                                        .map(student -> StudentDto.builder()
                                                .id(student.getId())
                                                .name(student.getStudentName())
                                                .lessonId(student.getLesson().getId())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


    private Classroom toEntity(ClassroomDto entity) {
        return Classroom.builder()
                .id(entity.getId())
                .name(entity.getName())
                .school(schoolService.findBySchoolId(entity.getSchoolId()))
                .lessons(entity.getLessons().stream().map(lesson ->
                        Lesson.builder()
                                .id(lesson.getId())
                                .name(lesson.getName())
                                .classroom(classroomRepository.findById(lesson.getClassroomId()).get())
                                .teacher(teacherService.findTeacherById(lesson.getTeacher().getId()))
                                .studentList(lesson.getStudentList().stream()
                                        .map(studentDto ->
                                                Student.builder()
                                                        .id(studentDto.getId())
                                                        .studentName(studentDto.getName())
                                                        .lesson(lessonService.findLessonById(studentDto.getLessonId()))
                                                        .build()
                                        )
                                        .collect(Collectors.toList())
                                )
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }


    @Override
    public void delete(String id) {
        classroomRepository.deleteById(Integer.parseInt(id));
    }

    @Override
    public ClassroomDto update(String id, ClassroomDto entity) {

        return ClassroomDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lessons(entity.getLessons())
                .schoolId(entity.getSchoolId())
                .build();
    }
}
