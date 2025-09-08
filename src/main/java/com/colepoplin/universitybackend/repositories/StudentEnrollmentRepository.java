package com.colepoplin.universitybackend.repositories;


import com.colepoplin.universitybackend.domain.entities.StudentEnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollmentEntity, Long> {
}
