package com.project.demo.services.StudentService;

import com.project.demo.controllers.StudentController.StudentRequest;
import com.project.demo.models.Student;
import com.project.demo.repositories.StudentRepository.StudentRepository;
import com.project.demo.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public String getPassword(String email) {
        Student student = repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User is Not Found")
        );
        student.setAvailable(true);
        return student.getPassword();

    }

    public void populateStudent(StudentRequest studentRequest) {
        String password = Utils.generatePassword();
        Student student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .password(password)
                .available(false)
                .build();
        repository.save(student);
    }
}