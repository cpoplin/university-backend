package com.colepoplin.universitybackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private StudentEntity student;

    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    //might want to set this to behave in a way that a student is
    //initialized in a course with this false as a method, with another
    //method to set it to true when the student completed the course
    private boolean isCompleted;

    //likely to be null much of the time;
    private float grade;
}
