package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.dto.LabDTO;
import com.AF_Assessment.AF_Assessment.model.Lab;
import com.AF_Assessment.AF_Assessment.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addlab")
    public ResponseEntity<Lab> addLab(@RequestBody LabDTO dto){
        Lab result = null;
        try{
            result = labService.addLab(dto);
            return new ResponseEntity<Lab>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateLab/{labId}")
    public ResponseEntity<Lab> updateLab(@PathVariable String labId, @RequestBody LabDTO labDTO) {
        Lab updatedLab = labService.updateLab(labId, labDTO);
        return new ResponseEntity<>(updatedLab, HttpStatus.OK);
    }

    @DeleteMapping("/lab/{labId}")
    public ResponseEntity<Void> deleteLab(@PathVariable String labId) {
        labService.deleteLabById(labId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
