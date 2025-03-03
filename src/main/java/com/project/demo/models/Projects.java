package com.project.demo.models;

import com.project.demo.models.Enumerations.Seance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

public class Projects {
    long id;
    String name;
    String code;
    String note;
    LocalDateTime date;

    ClassRoom room;
    @Enumerated(EnumType.STRING)
    Seance seance;

    Student student1;
    Student student2;

    Professor encadrant;
    Professor raporteur;
    Professor president;


}
