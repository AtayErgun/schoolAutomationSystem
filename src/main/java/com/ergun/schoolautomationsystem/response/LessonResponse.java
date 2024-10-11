package com.ergun.schoolautomationsystem.response;

import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.dto.TeacherDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
    private int id;
    private String name;
    private int classroomId;
    private List<StudentDto> studentList;
    private TeacherDto teacher;
}
