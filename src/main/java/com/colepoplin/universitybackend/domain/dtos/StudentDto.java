package com.colepoplin.universitybackend.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;

    private boolean enrolled;

    private String name;

    private String email;

    private List<StudentEnrollmentDto> enrollments;
}
