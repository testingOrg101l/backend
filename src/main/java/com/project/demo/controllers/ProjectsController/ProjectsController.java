package com.project.demo.controllers.ProjectsController;

import com.project.demo.models.Projects;
import com.project.demo.services.ProjectsService.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectsController {
    private final ProjectsService service;

    @PostMapping
    public ResponseEntity<Projects> createProjects(@RequestBody Projects projects) {
        return ResponseEntity.ok(service.createOrUpdateProjects(projects));
    }

    @GetMapping
    ResponseEntity<List<Projects>> getAllProjects() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjects(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getProjects(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjects(
            @PathVariable("id") Long id
    ) {
        service.deleteProjects(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity<Projects> updateEtudiant(@RequestBody Projects projects) {
        return ResponseEntity.ok(service.createOrUpdateProjects(projects));
    }

    @PostMapping("/assignStudent/{projectId}/{studentId}")
    public ResponseEntity<Void> assignStudentToProject(
            @PathVariable Long projectId,
            @PathVariable Long studentId
    ) {
        service.assignStudentToProject(projectId, studentId);
        return ResponseEntity.ok().build();
    }
}
