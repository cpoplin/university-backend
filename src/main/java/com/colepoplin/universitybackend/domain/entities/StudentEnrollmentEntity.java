package com.colepoplin.universitybackend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_enrollments")
public class StudentEnrollmentEntity {
    //this class creates the entity for the student enrollments, which is
    //to say each instance of this class is a class that a student took
    //or is taking
    @Id
    private Long enrollmentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    private Long courseId;
}
