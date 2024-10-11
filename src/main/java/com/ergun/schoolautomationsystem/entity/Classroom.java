package com.ergun.schoolautomationsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Lesson> lessons;


}
