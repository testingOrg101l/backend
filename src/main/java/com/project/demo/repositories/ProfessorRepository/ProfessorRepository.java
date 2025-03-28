package com.project.demo.repositories.ProfessorRepository;

import com.project.demo.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}