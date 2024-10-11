package com.ergun.schoolautomationsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private int id;
    private String name;
    private int lessonId;

}
