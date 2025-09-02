package com.colepoplin.universitybackend.mappers.impl;

import com.colepoplin.universitybackend.domain.dtos.CourseDto;
import com.colepoplin.universitybackend.domain.entities.CourseEntity;
import com.colepoplin.universitybackend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseMapperImpl implements Mapper<CourseEntity, CourseDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CourseDto mapToDto(CourseEntity courseEntity){
        return modelMapper.map(courseEntity, CourseDto.class);
    }

    @Override
    public CourseEntity mapToEntity(CourseDto courseDto){
        return modelMapper.map(courseDto, CourseEntity.class);
    }

}
