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

    public LectureHall updateLectureHall(String hallId, LectureHallDTO dto) {
        try {
            // Validate DTO inputs
            validateDTO(dto);

            // Check if the lecture hall exists
            Optional<LectureHall> existingHallOptional = lectureHallRepository.findBy_id(hallId);
            if (existingHallOptional.isEmpty()) {
                throw new IllegalArgumentException("Lecture hall with ID " + hallId + " does not exist");
            }

            // Get the existing lecture hall
            LectureHall existingHall = existingHallOptional.get();

            // Update the existing lecture hall with new data
            existingHall.setType(dto.getType().toLowerCase());
            existingHall.setHallCode(dto.getHallCode());
            existingHall.setDescription(dto.getDescription());
            // Update timestamp
            existingHall.setUpdatedAt(Instant.now());

            // Save the updated lecture hall
            return lectureHallRepository.save(existingHall);
        } catch (IllegalArgumentException e) {
            // Log the error and rethrow for handling in controller
            throw e;
        } catch (Exception e) {
            // Log unexpected errors and wrap them in a runtime exception for handling
            throw new RuntimeException("Failed to update lecture hall", e);
        }
    }

    private void validateDTO(LectureHallDTO dto) {
        // Perform validation on DTO fields (e.g., check for null or empty values)
        if (dto == null || dto.getHallCode() == null || dto.getHallCode().isEmpty()) {
            throw new IllegalArgumentException("Hall code must be provided");
        }
        // Add more validation checks as needed
    }

    private LectureHall map(LectureHallDTO dto){
        return LectureHall.builder()
                .type(dto.getType().toLowerCase())
                .hallCode(dto.getHallCode())
                .description(dto.getDescription())
                .createdAt(Instant.now())
                .build();
    }

    public boolean deleteLectureHallById(String hallId) {
        try {
            // Check if the lecture hall exists
            Optional<LectureHall> optionalLectureHall = lectureHallRepository.findBy_id(hallId);
            if (optionalLectureHall.isPresent()) {
                // If the lecture hall exists, delete it
                lectureHallRepository.deleteBy_id(hallId);
                return true; // Indicate successful deletion
            } else {
                // If the lecture hall does not exist, return false
                return false;
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return false; // Indicate deletion failure
        }
    }
    
}
