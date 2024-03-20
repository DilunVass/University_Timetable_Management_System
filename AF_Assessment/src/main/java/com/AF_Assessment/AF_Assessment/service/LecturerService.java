package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }
}
