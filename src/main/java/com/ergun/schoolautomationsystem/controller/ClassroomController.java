package com.ergun.schoolautomationsystem.controller;

import com.ergun.schoolautomationsystem.dto.ClassroomDto;
import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.entity.Classroom;
import com.ergun.schoolautomationsystem.request.ClassroomRequest;
import com.ergun.schoolautomationsystem.response.ClassroomResponse;
import com.ergun.schoolautomationsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public ClassroomResponse create(@RequestBody ClassroomRequest classroomRequest) {
        return toResponse(classroomService.create(toEntity(classroomRequest)));
    }

    @GetMapping
    public ClassroomResponse get(@RequestParam(value = "classroomId") String classroomId) {
       return toResponse(classroomService.get(classroomId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        classroomService.delete(id);
    }

    @PutMapping("/{id}")
    public ClassroomResponse update(@PathVariable(value = "id") String id, @RequestBody ClassroomRequest classroomRequest) {
        return toResponse(classroomService.update(id, toEntity(classroomRequest)));
    }

    private ClassroomResponse toResponse(ClassroomDto classroomDto) {
        return ClassroomResponse.builder()
                .id(classroomDto.getId())
                .lessons(classroomDto.getLessons())
                .schoolId(classroomDto.getSchoolId())
                .name(classroomDto.getName())
                .build();
//        ClassroomResponse classroomResponse=new ClassroomResponse();
//        classroomResponse.setId(classroomDto.getId());
//        classroomResponse.setName(classroomDto.getName());
//        List<LessonDto> lessonList=new ArrayList<>();
//        for (LessonDto lessonDto:classroomDto.getLessons()){
//            lessonDto.setId(classroomDto.getId());
//            lessonDto.setName(classroomDto.getName());
//            lessonDto.setClassroomId();
//        }
//        classroomResponse.setLessons(lessonList);
    }

    private ClassroomDto toEntity(ClassroomRequest classroomRequest) {
        return ClassroomDto.builder()
                .schoolId(classroomRequest.getSchoolId())
                .name(classroomRequest.getName())
                //hata veriyor __|| .lessons(classroomRequest.getLessons().)
                .build();
    }

}
