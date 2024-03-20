package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    public List<Subjects> getAllSubjects(){
        return subjectsRepository.findAll();
    }
}
