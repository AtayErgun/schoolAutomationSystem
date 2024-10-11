package com.ergun.schoolautomationsystem.request;

import com.ergun.schoolautomationsystem.entity.Lesson;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomRequest {
    public String name;
    public int schoolId;
}
