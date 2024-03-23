package com.AF_Assessment.AF_Assessment.service;



import com.AF_Assessment.AF_Assessment.dto.LectureHallDTO;
import com.AF_Assessment.AF_Assessment.model.LectureHall;
import com.AF_Assessment.AF_Assessment.repository.LectureHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LectureHallService {

    @Autowired
    private LectureHallRepository lectureHallRepository;

    public List<LectureHall> getAllLectureHalls(){
        return lectureHallRepository.findAll();
    }

    public LectureHall addLectureHall(LectureHallDTO dto){
        try{
            Optional<LectureHall> existing = lectureHallRepository.findLectureHallByHallCode(dto.getHallCode());

            LectureHall savedLectureHall = null;

            if (existing.isPresent()){
                throw new IllegalArgumentException("Hall is already exists");
            }else {
                LectureHall lectureHall = map(dto);
                lectureHall.setCreatedAt(Instant.now());

                lectureHallRepository.save(lectureHall);

                dto.setId(savedLectureHall.get_id());
                savedLectureHall = lectureHall;
                return savedLectureHall;
            }
        }catch (Exception e){
            return null;
        }
    }

    private LectureHall map(LectureHallDTO dto){
        return LectureHall.builder()
                .type(dto.getType().toLowerCase())
                .hallCode(dto.getHallCode())
                .description(dto.getDescription())
                .createdAt(Instant.now())
                .build();
    }
    
}
