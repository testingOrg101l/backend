package com.project.demo.services.StudentService;

import com.project.demo.models.Student;
import com.project.demo.repositories.StudentRepository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Student createOrUpdateStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    public Student getStudent(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}