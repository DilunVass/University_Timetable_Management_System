package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.dto.SubjectDTO;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SubjectController {

    @Autowired
    private SubjectsService subjectsService;

    @GetMapping("subjects")
    public ResponseEntity<List<Subjects>> getAllSubjects(){
        return new ResponseEntity<List<Subjects>>(subjectsService.getAllSubjects(), HttpStatus.OK);
    }

//    @PostMapping("/addsubject")
//    public ResponseEntity<Object> addSubject(@RequestBody SubjectDTO subjectDTO){
//        try{
//            SubjectDTO result = subjectsService.addSubject(subjectDTO);
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        }catch (Exception e){
//            return null;
//        }
//    }

    @PostMapping("/addsubject")
    public ResponseEntity<Object> addSubject(@RequestBody SubjectDTO subjectDTO) {

        Subjects result = null;
//        try {
//            SubjectDTO result = subjectsService.addSubject(subjectDTO);
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            // Handle specific exceptions with meaningful messages
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            // Handle any other unexpected exceptions
//            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        try {
            result = subjectsService.addSubject(subjectDTO);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateSubject/{subjectId}")
    public ResponseEntity<Subjects> updateSubject(@PathVariable String subjectId, @RequestBody SubjectDTO updatedSubjectDTO) {
        // Call the service method to update the subject
        Subjects updatedSubject = subjectsService.updateSubject(subjectId, updatedSubjectDTO);

        // Check if the subject was successfully updated
        if (updatedSubject != null) {
            return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteSubject/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectId) {
        boolean deleted = subjectsService.deleteSubject(subjectId);
        if (deleted) {
            return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Subject not found or unable to delete", HttpStatus.NOT_FOUND);
        }
    }

}
