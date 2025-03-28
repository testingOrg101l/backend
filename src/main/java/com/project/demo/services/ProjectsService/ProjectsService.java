package com.project.demo.services.ProjectsService;


import com.project.demo.models.Projects;
import com.project.demo.repositories.ProjectsRepository.ProjectsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final ProjectsRepository repository;

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
}