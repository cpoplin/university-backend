package com.colepoplin.universitybackend.services.impl;

import com.colepoplin.universitybackend.domain.dtos.StudentDto;
import com.colepoplin.universitybackend.domain.entities.StudentEntity;
import com.colepoplin.universitybackend.mappers.Mapper;
import com.colepoplin.universitybackend.repositories.StudentRepository;
import com.colepoplin.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public StudentDto createStudent(StudentDto studentDto){
        StudentEntity studentEntity = studentMapper.mapToEntity(studentDto);
        studentRepository.save(studentEntity);
        return studentMapper.mapToDto(studentEntity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

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
        return studentRepository.findById(id).map(existingEntity ->{
            Optional.ofNullable(studentDto.getEmail()).ifPresent(existingEntity::setEmail);
            Optional.ofNullable(studentDto.getName()).ifPresent(existingEntity::setName);
            StudentEntity savedStudentEntity = studentRepository.save(existingEntity);
            return studentMapper.mapToDto(savedStudentEntity);
        }).orElseThrow(()-> new RuntimeException("Student partial Update Failed, it should not fail here there are checks before this"));
    }

    @Override
    public StudentDto updateEnrollment(Long id) {
        return studentRepository.findById(id).map(existingEntity ->{
            if(existingEntity.isEnrolled()){
                existingEntity.setEnrolled(false);
            }
            else{
                existingEntity.setEnrolled(true);
            }
            StudentEntity savedStudentEntity = studentRepository.save(existingEntity);
            return studentMapper.mapToDto(savedStudentEntity);
        }).orElseThrow(()-> new RuntimeException("Student update enrollment Failed, it should not fail here there are checks before this"));
        //test this
    }
}
