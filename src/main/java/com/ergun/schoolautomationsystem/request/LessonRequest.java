package com.ergun.schoolautomationsystem.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequest {
    public String name;
    public int classroomId;
}
