package com.AF_Assessment.AF_Assessment.service;


import com.AF_Assessment.AF_Assessment.model.Lab;
import com.AF_Assessment.AF_Assessment.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    public List<Lab> getAllLabs(){
        return labRepository.findAll();
    }
}
