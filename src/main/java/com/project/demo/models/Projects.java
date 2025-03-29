package com.project.demo.models;

import com.project.demo.models.Enumerations.Seance;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Projects {
    @Id
    @GeneratedValue
    long id;
    String name;
    String code;
    String note;

    @OneToMany
    List<Student> students;


    @ManyToOne
    Professor encadrant;

    @ManyToOne
    Professor rapporteur;

    @ManyToOne
    Professor president;

}
