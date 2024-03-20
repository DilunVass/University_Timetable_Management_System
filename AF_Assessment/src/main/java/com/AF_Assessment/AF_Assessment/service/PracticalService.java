package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.Practical;
import com.AF_Assessment.AF_Assessment.repository.PracticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticalService {

    @Autowired
    private PracticalRepository practicalRepository;

    public List<Practical> getAllPracticals(){
        return practicalRepository.findAll();
    }
}
