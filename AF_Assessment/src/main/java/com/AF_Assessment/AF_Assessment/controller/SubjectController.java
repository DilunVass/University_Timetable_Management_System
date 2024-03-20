package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SubjectController {

    @Autowired
    private SubjectsService subjectsService;

    @GetMapping("subjects")
    public ResponseEntity<List<Subjects>> getAllSubjects(){
        return new ResponseEntity<List<Subjects>>(subjectsService.getAllSubjects(), HttpStatus.OK);
    }
}
