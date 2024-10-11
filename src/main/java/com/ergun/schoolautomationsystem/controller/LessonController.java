package com.ergun.schoolautomationsystem.controller;

import com.ergun.schoolautomationsystem.dto.LessonDto;
import com.ergun.schoolautomationsystem.dto.StudentDto;
import com.ergun.schoolautomationsystem.request.LessonRequest;
import com.ergun.schoolautomationsystem.response.LessonResponse;
import com.ergun.schoolautomationsystem.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public LessonResponse create(@RequestBody LessonRequest lessonRequest) {
        return toResponse(lessonService.crate(toEntity(lessonRequest)));
    }
    @GetMapping
    public LessonResponse get(@RequestParam(value = "lessonId")String id){
        return toResponse(lessonService.get(id));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id")String id){
        lessonService.delete(id);
    }
    @PutMapping("/{id}")
    public LessonResponse update(@PathVariable(name = "id")String id,@RequestBody LessonRequest lessonRequest){
        return toResponse(lessonService.update(id,toEntity(lessonRequest)));
    }

    private LessonResponse toResponse(LessonDto dto) {
        return LessonResponse.builder()
                .id(dto.getId())
                .studentList(dto.getStudentList())
                .teacher(dto.getTeacher())
                .name(dto.getName())
                .classroomId(dto.getClassroomId())
                .build();
//        LessonResponse lessonResponse = new LessonResponse();
//        lessonResponse.setId(dto.getId());
//        lessonResponse.setName(dto.getName());
//        lessonResponse.setClassroomId(dto.getClassroomId());
//        lessonResponse.setTeacher(dto.getTeacher());
//        List<StudentDto> studentList = new ArrayList<>();
//        for (StudentDto studentDto : dto.getStudentList()) {
//            StudentDto dto1 = new StudentDto();
//            dto1.setId(studentDto.getId());
//            dto1.setName(studentDto.getName());
//            studentList.add(dto1);
//        }
//        lessonResponse.setStudentList(studentList);
//        return lessonResponse;
    }

    private LessonDto toEntity(LessonRequest lessonRequest) {
        return LessonDto.builder()
                .name(lessonRequest.getName())
                .classroomId(lessonRequest.getClassroomId())
                .build();
//        LessonDto lessonDto = new LessonDto();
//        lessonDto.setClassroomId(lessonRequest.classroomId);
//        lessonDto.setName(lessonRequest.name);
//        return lessonDto;
    }

}
