package com.AF_Assessment.AF_Assessment.controller;


import com.AF_Assessment.AF_Assessment.dto.StudentDTO;
import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping("addstudent")
    public ResponseEntity<Object> addStudent(@RequestBody StudentDTO dto){
        ResponseEntity result = null;

        try{
            result = studentService.addStudent(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String studentId, @RequestBody StudentDTO updatedStudentDTO) {
        // Call the service method to update the student
        Student updatedStudent = studentService.updateStudent(studentId, updatedStudentDTO);

        if (updatedStudent != null) {
            // Student successfully updated
            return ResponseEntity.ok(updatedStudent);
        } else {
            // Student not found or failed to update
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
