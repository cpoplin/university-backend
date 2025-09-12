package com.colepoplin.universitybackend.controllers;


import com.colepoplin.universitybackend.domain.dtos.StudentDto;
import com.colepoplin.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        //improper cases, these values should not be set here by this API contract
        if(studentDto.getStudentId() != null || studentService.ifExists(studentDto.getStudentId())
                || studentDto.getEnrollments() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
    //could easily modify to return paginated data
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        Optional<StudentDto> foundStudent = studentService.findOne(id);
        return foundStudent.map(studentDto ->{
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PatchMapping("students/{id}")
    public ResponseEntity<StudentDto> partialUpdateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        /*
        I am electing to leave modifying a student's enrollment status to its own method, for
        security and for simplicity of the api.  I feel that enrollment status is important enough to justify its own
        endpoint.  Enrolled courses non inclusion is just standard decoupling.
         */
        if(!studentService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(studentService.partialUpdate(id, studentDto), HttpStatus.OK);
        }
    }
    //PUT fullUpdate by my current vision and understanding would serve no purpose, due to coupling and security concerns

    @PatchMapping("students/{id}/enrollment")
    public ResponseEntity<StudentDto> switchStudentEnrollment(@PathVariable Long id) {
        //TODO - be 100% sure of the security of this endpoint
        if (!studentService.ifExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentService.updateEnrollment(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id){
        if(!studentService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



}
