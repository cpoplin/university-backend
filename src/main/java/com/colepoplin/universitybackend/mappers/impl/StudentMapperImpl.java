package com.colepoplin.universitybackend.mappers.impl;

import com.colepoplin.universitybackend.domain.dtos.StudentDto;
import com.colepoplin.universitybackend.domain.entities.StudentEntity;
import com.colepoplin.universitybackend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentMapperImpl implements Mapper<StudentEntity, StudentDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto mapToDto(StudentEntity studentEntity){
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public StudentEntity mapToEntity(StudentDto studentDto){
        return modelMapper.map(studentDto, StudentEntity.class);
    }

}
