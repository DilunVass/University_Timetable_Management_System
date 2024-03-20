package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
