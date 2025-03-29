package com.project.demo.services.ProjectsService;


import com.project.demo.models.Projects;
import com.project.demo.repositories.ProjectsRepository.ProjectsRepository;
import com.project.demo.repositories.StudentRepository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final ProjectsRepository repository;
    private final StudentRepository studentRepository;

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
        var student = studentRepository.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student Not Found"));
        if(project.getStudents().size() >= 2) {
            throw new IllegalStateException("Project already has 2 students");
        }
        if (project.getStudents().contains(student)) {
            throw new IllegalStateException("Student already assigned to this project");
        }
        project.getStudents().add(student);
        repository.save(project);
    }
}