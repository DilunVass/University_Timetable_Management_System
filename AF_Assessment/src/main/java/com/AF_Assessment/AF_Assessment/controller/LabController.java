package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.model.Lab;
import com.AF_Assessment.AF_Assessment.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LabController {
    @Autowired
    private LabService labService;

    @GetMapping("/labs")
    public ResponseEntity<List<Lab>> getAllLabs(){
        return new ResponseEntity<List<Lab>>(labService.getAllLabs(), HttpStatus.OK);
    }
}