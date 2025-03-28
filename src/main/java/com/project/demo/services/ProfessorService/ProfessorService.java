package com.project.demo.services.ProfessorService;


import com.project.demo.models.Professor;
import com.project.demo.repositories.ProfessorRepository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository repository;

    public Professor createOrUpdateProfessor(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> getAllProfessor() {
        return repository.findAll();
    }

    public Professor getProfessor(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor Not Found"));
    }

    public void deleteProfessor(Long id) {
        repository.deleteById(id);
    }
}