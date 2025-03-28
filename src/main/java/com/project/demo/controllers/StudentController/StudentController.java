package com.project.demo.controllers.StudentController;


import com.project.demo.models.Student;
import com.project.demo.services.StudentService.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.createOrUpdateStudent(student));
    }

    @GetMapping
    ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(service.getAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getStudent(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("id") Long id
    ) {
        service.deleteStudent(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity<Student> updateEtudiant(@RequestBody Student student) {
        return ResponseEntity.ok(service.createOrUpdateStudent(student));
    }
}
