package com.ergun.schoolautomationsystem.response;

import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.entity.Lesson;
import com.ergun.schoolautomationsystem.entity.School;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponse {
    private int id;
    private String name;
    private int schoolId;
    private List<LessonDto> lessons;

}
