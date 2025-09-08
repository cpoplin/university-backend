package com.colepoplin.universitybackend.services;

import com.colepoplin.universitybackend.domain.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
}
