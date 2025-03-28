package com.project.demo.controllers.ProfessorController;

import com.project.demo.models.Professor;
import com.project.demo.services.ProfessorService.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok(service.createOrUpdateProfessor(professor));
    }

    @GetMapping
    ResponseEntity<List<Professor>> getAllProfessor() {
        return ResponseEntity.ok(service.getAllProfessor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getProfessor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(
            @PathVariable("id") Long id
    ) {
        service.deleteProfessor(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity<Professor> updateEtudiant(@RequestBody Professor professor) {
        return ResponseEntity.ok(service.createOrUpdateProfessor(professor));
    }
}
