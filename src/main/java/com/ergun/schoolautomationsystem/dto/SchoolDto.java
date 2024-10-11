package com.ergun.schoolautomationsystem.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {
        private int id;
        private String name;
        private ManagerDto manager;
        private List<ClassroomDto> classroomList;
}
