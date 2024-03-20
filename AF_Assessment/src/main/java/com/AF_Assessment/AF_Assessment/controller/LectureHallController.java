package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.model.LectureHall;
import com.AF_Assessment.AF_Assessment.service.LectureHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LectureHallController {

    @Autowired
    private LectureHallService lectureHallService;

    @GetMapping("/lectureHalls")
    public ResponseEntity<List<LectureHall>> getAllLectureHalls(){
        return new ResponseEntity<List<LectureHall>>(lectureHallService.getAllLectureHalls(), HttpStatus.OK);
    }
}
