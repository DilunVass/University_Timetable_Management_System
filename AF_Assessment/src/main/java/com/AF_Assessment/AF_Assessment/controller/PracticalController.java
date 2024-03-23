package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.dto.PracticalDTO;
import com.AF_Assessment.AF_Assessment.model.Practical;
import com.AF_Assessment.AF_Assessment.repository.PracticalRepository;
import com.AF_Assessment.AF_Assessment.service.PracticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PracticalController {

    @Autowired
    private PracticalService practicalService;

    @GetMapping("/practicals")
    public ResponseEntity<List<Practical>> getAllPracticals(){
        return new ResponseEntity<List<Practical>>(practicalService.getAllPracticals(), HttpStatus.OK);
    }

    @PostMapping("/addPractical")
    public ResponseEntity<Object> addPractical(@RequestBody PracticalDTO dto){
        try{
            Practical practical = practicalService.addPractical(dto);
            return new ResponseEntity<>(practical, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
