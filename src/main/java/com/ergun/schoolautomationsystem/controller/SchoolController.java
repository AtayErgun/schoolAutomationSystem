package com.ergun.schoolautomationsystem.controller;

import com.ergun.schoolautomationsystem.dto.SchoolDto;
import com.ergun.schoolautomationsystem.request.SchoolRequest;
import com.ergun.schoolautomationsystem.response.SchoolResponse;
import com.ergun.schoolautomationsystem.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public SchoolResponse create(@RequestBody SchoolRequest schoolRequest) {
        return toResponse(schoolService.save(toEntity(schoolRequest)));
    }

    @GetMapping
    public SchoolResponse get(@RequestParam(value = "id") String id) {
        return toResponse(schoolService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        schoolService.delete(id);
    }

    @PutMapping("/{id}")
    public SchoolResponse update(@PathVariable(name = "id") String id, @RequestBody SchoolRequest schoolRequest) {
        return toResponse(schoolService.update(id, toEntity(schoolRequest)));
    }

    private SchoolDto toEntity(SchoolRequest schoolRequest) {
        return SchoolDto.builder()
                .name(schoolRequest.name).build();
    }

    private SchoolResponse toResponse(SchoolDto schoolDto) {
        return SchoolResponse.builder()
                .id(schoolDto.getId())
                .name(schoolDto.getName())
                .manager(schoolDto.getManager())
                .classrooms(schoolDto.getClassroomList())
                .build();
    }

}
