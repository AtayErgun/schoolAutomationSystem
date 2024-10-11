package com.ergun.schoolautomationsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String studentName;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;


}





