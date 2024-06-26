package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.dto.LectureDTO;
import com.AF_Assessment.AF_Assessment.model.Lecture;
import com.AF_Assessment.AF_Assessment.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping("/lectures")
    public ResponseEntity<List<Lecture>> getAllLectures(){
        return new ResponseEntity<List<Lecture>>(lectureService.getAllLectures(), HttpStatus.OK);
    }

    @PostMapping("/addLecture")
    public ResponseEntity<Object> addLecture(@RequestBody LectureDTO dto){
        try{
            Lecture result = lectureService.addLecture(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateLecture/{lectureId}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable String lectureId, @RequestBody LectureDTO dto) {
        Lecture updatedLecture = lectureService.updateLecture(lectureId, dto);
        if (updatedLecture != null) {
            return ResponseEntity.ok(updatedLecture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteLecture/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable String id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }
}
