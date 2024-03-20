package com.AF_Assessment.AF_Assessment.service;


import com.AF_Assessment.AF_Assessment.model.LectureHall;
import com.AF_Assessment.AF_Assessment.repository.LectureHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureHallService {

    @Autowired
    private LectureHallRepository lectureHallRepository;

    public List<LectureHall> getAllLectureHalls(){
        return lectureHallRepository.findAll();
    }
    
}
