package com.AF_Assessment.AF_Assessment.service;


import com.AF_Assessment.AF_Assessment.dto.LabDTO;
import com.AF_Assessment.AF_Assessment.model.Lab;
import com.AF_Assessment.AF_Assessment.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    public List<Lab> getAllLabs(){
        return labRepository.findAll();
    }

    public Lab addLab(LabDTO dto){
        try{
            Optional<Lab> existing = labRepository.findLabByLabCode(dto.getLabCode());

            Lab save = null;
            if (existing.isPresent()){
                throw new IllegalArgumentException("Lab is already exists");
            }
            else {
                Lab lab = map(dto);

                lab.setCreatedAt(Instant.now());

                labRepository.save(lab);

                dto.setId(save.get_id());
                save = lab;
                return save;
            }

        }catch (Exception e){
            return null;
        }
    }


    private Lab map(LabDTO dto){
        return Lab.builder()
                .type(dto.getType())
                .labCode(dto.getLabCode())
                .description(dto.getDescription())
                .createdAt(Instant.now())
                .build();
    }
}
