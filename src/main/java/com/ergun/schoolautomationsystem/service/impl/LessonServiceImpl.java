package com.ergun.schoolautomationsystem.service.impl;

import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.dto.TeacherDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.entity.Lesson;
import com.ergun.schoolautomationsystem.entity.Student;
import com.ergun.schoolautomationsystem.repository.LessonRepository;
import com.ergun.schoolautomationsystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;

    private final TeacherServiceImpl teacherService;

    private final ClassroomServiceImpl classroomService;


    @Override
    public LessonDto crate(LessonDto entity) {
        return toDto(repository.save(toEntity(entity)));
    }

    @Override
    public LessonDto get(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public LessonDto update(String id, LessonDto entity) {
        LessonDto lessonDto = toDto(repository.findById(Integer.parseInt(id)).get());
        lessonDto.setId(entity.getId());
        lessonDto.setName(entity.getName());
        lessonDto.setClassroomId(entity.getClassroomId());
        List<StudentDto> studentList = new ArrayList<>();
        for (StudentDto studentDto : entity.getStudentList()) {
            studentDto.setId(entity.getId());
            studentDto.setName(entity.getName());
            studentList.add(studentDto);
        }
        lessonDto.setStudentList(studentList);
        lessonDto.setTeacher(entity.getTeacher());
        return toDto(repository.save(toEntity(lessonDto)));
    }

    public Lesson toEntity(LessonDto entity) {
        return Lesson.builder()
                .id(entity.getId())
                .name(entity.getName())
                .classroom(classroomService.findClassroomById(entity.getClassroomId()))
                .teacher(teacherService.findTeacherById(entity.getTeacher().getId()))
                .studentList(entity.getStudentList().stream()
                        .map(student -> Student.builder()
                                .id(student.getId())
                                .studentName(student.getName()).build()).collect(Collectors.toList())).build();

    }

    public LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
                .name(lesson.getName())
                .id(lesson.getId())
                .classroomId(lesson.getClassroom().getId())
                .teacher(teacherService.get(String.valueOf(lesson.getTeacher().getId())))
                .studentList(lesson.getStudentList().stream()
                        .map(student -> StudentDto.builder()
                                .id(student.getId())
                                .name(student.getStudentName())
                                .lessonId(student.getLesson().getId())
                                .build()).collect(Collectors.toList())).build();

        //burası çalışmadı o yüzden ıgnore edildi.studentList(lesson.getStudentList())

//        LessonDto lessonDto = new LessonDto();
//        lessonDto.setId(lesson.getId());
//        lessonDto.setName(lesson.getName());
//        lessonDto.setClassroomId(lesson.getClassroom().getId());
//        lessonDto.setTeacher(lessonDto.getTeacher());
//        List<StudentDto> studentList = new ArrayList<>();
//        for (StudentDto studentDto : lesson.getStudentList()) {
//            studentDto.setId(lesson.getId());
//            studentDto.setName(lesson.getName());
//            studentList.add(studentDto);
//        }
//        lessonDto.setStudentList(studentList);
//        return lessonDto;
    }

    public Lesson findLessonById(int lessonId) {
        return repository.findById(lessonId).get();
    }
}
