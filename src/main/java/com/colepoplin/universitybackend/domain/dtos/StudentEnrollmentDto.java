package com.colepoplin.universitybackend.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollmentDto {

    private Long enrollmentId;

    //private StudentDto student;
    //causes infinite recursion

    private CourseDto course;

    private boolean isCompleted;

    private float grade;
}
