package com.colepoplin.universitybackend.services;

import com.colepoplin.universitybackend.domain.dtos.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> getAllStudents();

    Optional<StudentDto> findOne(Long id);

    boolean ifExists(Long id);

    StudentDto partialUpdate(Long id, StudentDto studentDto);
}
