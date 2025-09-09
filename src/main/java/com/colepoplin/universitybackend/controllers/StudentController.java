package com.colepoplin.universitybackend.controllers;


import com.colepoplin.universitybackend.domain.dtos.StudentDto;
import com.colepoplin.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

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

    @GetMapping("students/{id}")
    public ResponseEntity<StudentDto> partialUpdateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        if(!studentService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(studentService.partialUpdate(id, studentDto), HttpStatus.OK);
        }
    }



}
