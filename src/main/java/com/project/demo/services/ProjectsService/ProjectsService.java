package com.project.demo.services.ProjectsService;


import com.project.demo.models.ProjectDTO;
import com.project.demo.models.Projects;
import com.project.demo.repositories.ProfessorRepository.ProfessorRepository;
import com.project.demo.repositories.ProjectsRepository.ProjectsRepository;
import com.project.demo.repositories.StudentRepository.StudentRepository;
import com.project.demo.services.ProfessorService.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final ProjectsRepository repository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;


    @Transactional
    public void saveSingle(ProjectDTO dto) {
        // 1) Validate unique code
        if (repository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Project code already exists: " + dto.getCode());
        }

        // 2) Load required encadrant
        var enc = professorRepository.findById(dto.getEncadrantId())
                .orElseThrow(() -> new IllegalArgumentException("Encadrant not found: " + dto.getEncadrantId()));

        // 3) Load optional rapporteur & president
        var rap = dto.getRapporteurId() == null
                ? null
                : professorRepository.findById(dto.getRapporteurId())
                .orElseThrow(() -> new IllegalArgumentException("Rapporteur not found: " + dto.getRapporteurId()));

        var pres = dto.getPresidentId() == null
                ? null
                : professorRepository.findById(dto.getPresidentId())
                .orElseThrow(() -> new IllegalArgumentException("President not found: " + dto.getPresidentId()));

        // 4) Load students (ensures list size ≥ 1 by DTO validation)
        var students = dto.getStudentIds().stream()
                .map(id -> studentRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id)))
                .toList();

        // 5) Build and save
        Projects p = Projects.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .note(dto.getNote())
                .encadrant(enc)
                .rapporteur(rap)
                .president(pres)
                .students(students)
                .build();

        repository.save(p);
    }


    public Projects createOrUpdateProjects(Projects projects) {
        return repository.save(projects);
    }

    public List<Projects> getAllProjects() {
        return repository.findAll();
    }

    public Projects getProjects(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Projects Not Found"));
    }

    public void deleteProjects(Long id) {
        repository.deleteById(id);
    }

    public void assignStudentToProject(long projectId, long studentId) {
        var project = getProjects(projectId);
        var student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        if (project.getStudents().size() >= 2) {
            throw new IllegalStateException("Project already has 2 students");
        }
        if (project.getStudents().contains(student)) {
            throw new IllegalStateException("Student already assigned to this project");
        }
        project.getStudents().add(student);
        repository.save(project);
    }

    public void assignProfessorToProject(long projectId, long encadrantId, String role) {
        var project = getProjects(projectId);
        var encadrant = professorRepository.findById(encadrantId).orElseThrow(() -> new EntityNotFoundException("Professor Not Found"));
        switch (role) {
            case "ENCADRANT":
                project.setEncadrant(encadrant);
                break;
            case "RAPPORTEUR":
                project.setRapporteur(encadrant);
                break;
            case "PRESIDENT":
                project.setPresident(encadrant);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role: " + role);
        }
        repository.save(project);
    }

}