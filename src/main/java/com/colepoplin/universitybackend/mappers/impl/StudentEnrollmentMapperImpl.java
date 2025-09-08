package com.colepoplin.universitybackend.mappers.impl;

import com.colepoplin.universitybackend.domain.dtos.StudentEnrollmentDto;
import com.colepoplin.universitybackend.domain.entities.StudentEnrollmentEntity;
import com.colepoplin.universitybackend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentEnrollmentMapperImpl implements Mapper<StudentEnrollmentEntity, StudentEnrollmentDto> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentEnrollmentDto mapToDto(StudentEnrollmentEntity studentEnrollmentEntity){
        return modelMapper.map(studentEnrollmentEntity, StudentEnrollmentDto.class);
    }

    @Override
    public StudentEnrollmentEntity mapToEntity(StudentEnrollmentDto studentEnrollmentDto){
        return modelMapper.map(studentEnrollmentDto, StudentEnrollmentEntity.class);
    }

}
