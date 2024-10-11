package com.ergun.schoolautomationsystem.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private int id;
    private String name;
    private int classroomId;
    private List<StudentDto> studentList;
    private TeacherDto teacher;

}
