package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.dto.LectureHallDTO;
import com.AF_Assessment.AF_Assessment.model.LectureHall;
import com.AF_Assessment.AF_Assessment.service.LectureHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addlecturehall")
    public  ResponseEntity<LectureHall> addLectureHall(@RequestBody LectureHallDTO dto){
        LectureHall result = null;
        try{
            result = lectureHallService.addLectureHall(dto);
            return new ResponseEntity<LectureHall>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
