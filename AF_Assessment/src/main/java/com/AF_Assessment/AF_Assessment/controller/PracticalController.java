package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.model.Practical;
import com.AF_Assessment.AF_Assessment.repository.PracticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PracticalController {

    @Autowired
    private PracticalRepository practicalRepository;

    @GetMapping("/pacticals")
    public ResponseEntity<List<Practical>> getAllPracticals(){
        return new ResponseEntity<List<Practical>>(practicalRepository.findAll(), HttpStatus.OK);
    }
}
