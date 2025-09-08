package com.colepoplin.universitybackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentId;

    private boolean enrolled;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true
    , mappedBy = "student")
    //the mappedBy here tells JPA that the StudentEnrollmentEntity is the owner of the relationship
    //and that it will store the foreign key in the DB, so no corresponding attribute
    //actually exists in the db for this java attribute here, rather to fetch all
    //the enrollments for a student, we fetch each StudentEnrollmentEntity with
    //the studentId as a foreign key
    @JsonManagedReference
    //this annotation makes it so that the list of enrollments doesn't contain the student,
    //preventing infinite recursion, also needs @JsonBackReference on the other side
    private List<StudentEnrollmentEntity> enrollments;

    // will contain the instance of many other entities

}
