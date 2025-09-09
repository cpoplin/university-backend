package com.colepoplin.universitybackend.services.impl;

import com.colepoplin.universitybackend.domain.dtos.StudentDto;
import com.colepoplin.universitybackend.domain.entities.StudentEntity;
import com.colepoplin.universitybackend.mappers.Mapper;
import com.colepoplin.universitybackend.repositories.StudentRepository;
import com.colepoplin.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Mapper<StudentEntity, StudentDto> studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();;
        return studentEntities.stream().map(studentMapper::mapToDto).toList();
    }

    @Override
    public Optional<StudentDto> findOne(Long id) {
        return studentRepository.findById(id).map(studentMapper::mapToDto);
    }

    @Override
    public boolean ifExists(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public StudentDto partialUpdate(Long id, StudentDto studentDto) {
        return null;
        //current job
    }
}
