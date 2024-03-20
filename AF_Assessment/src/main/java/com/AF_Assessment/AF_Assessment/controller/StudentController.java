package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
    }
}
