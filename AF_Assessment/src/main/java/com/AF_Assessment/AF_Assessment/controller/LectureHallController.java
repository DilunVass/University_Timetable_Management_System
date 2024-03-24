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

    @PutMapping("/updateLectureHall/{hallId}")
    public ResponseEntity<Object> updateLectureHall(@PathVariable("hallId") String hallId, @RequestBody LectureHallDTO dto) {
        try {
            LectureHall updatedHall = lectureHallService.updateLectureHall(hallId, dto);
            return ResponseEntity.ok(updatedHall);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update lecture hall");
        }
    }

    @DeleteMapping("deleteLectureHall/{hallId}")
    public ResponseEntity<String> deleteLectureHallById(@PathVariable String hallId) {
        boolean deleted = lectureHallService.deleteLectureHallById(hallId);
        if (deleted) {
            return new ResponseEntity<>("Lecture hall with ID " + hallId + " has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Lecture hall with ID " + hallId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
