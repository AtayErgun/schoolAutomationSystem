package com.ergun.schoolautomationsystem.response;

import com.ergun.schoolautomationsystem.dto.ClassroomDto;
import com.ergun.schoolautomationsystem.dto.ManagerDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.entity.Manager;
import lombok.*;


import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SchoolResponse {
    private int id;

    private String name;


    private ManagerDto manager;


    private List<ClassroomDto> classrooms;


}

