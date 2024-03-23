package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.dto.LecturerDTO;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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


    @PostMapping("/addlecturer")
    public ResponseEntity<Object> addLecturers(@RequestBody LecturerDTO dto){
        Lecturer result = null;

        try{
            result = lecturerService.addLecturer(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
