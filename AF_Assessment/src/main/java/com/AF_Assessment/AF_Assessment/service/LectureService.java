package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.Lecture;
import com.AF_Assessment.AF_Assessment.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    public List<Lecture> getAllLectures(){
        return lectureRepository.findAll();
    }
}
