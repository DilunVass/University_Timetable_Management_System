package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.dto.LecturerDTO;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;


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

    @GetMapping("/lecturers/{lecturerId}")
    public ResponseEntity<Object> getLecturerById(@PathVariable String lecturerId) {
        try {
            Optional<Lecturer> lecturer = lecturerService.getLecturerById(lecturerId);
            if (lecturer.isPresent()) {
                return new ResponseEntity<>(lecturer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Lecturer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/lecturerDelete/{id}")
    public ResponseEntity<String> deleteLecturerById(@PathVariable("id") String lecturerId) {
        if (lecturerService.deleteLecturerById(lecturerId)) {
            return new ResponseEntity<>("Lecturer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete lecturer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("updateLecture/{id}")
    public ResponseEntity<Object> updateLecturer(@PathVariable String id, @RequestBody LecturerDTO dto){
        dto.setLecturerId(id); // Set the lecturer ID from the path variable
        boolean updated = lecturerService.updateLecturer(dto);
        if (updated) {
            return new ResponseEntity<>("Lecturer updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update lecturer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
