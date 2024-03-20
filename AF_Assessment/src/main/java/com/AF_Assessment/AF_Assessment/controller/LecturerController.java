package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("lecturers")
    public ResponseEntity<List<Lecturer>> getAllLectures(){
        return new ResponseEntity<List<Lecturer>>(lecturerService.getAllLecturers(), HttpStatus.OK);
    }
}
