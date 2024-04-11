package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.LecturerDTO;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import com.AF_Assessment.AF_Assessment.util.ExtraUtilities;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import java.security.KeyException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LecturerService {

//    @Autowired
//    private SubjectsRepository subjectsRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final PasswordEncoder passwordEncoder;

    private String passwordEncoder(String pass){
        return passwordEncoder.encode(pass);
    }


    public Lecturer addLecturer(LecturerDTO lecturerDTO) {

        try{
            //checking present lecturers use this email
            Optional<Lecturer> existing = lecturerRepository.findLecturerByEmail(lecturerDTO.getEmail());

            Lecturer savedLecturer = null;
            if (existing.isPresent()) {
                throw new IllegalArgumentException("Email already exists");
            }else {
                // Validate email
                if (!ExtraUtilities.isEmailValid(lecturerDTO.getEmail())) {
                    throw new IllegalArgumentException("Invalid email");
                }

                Lecturer lecturer = map(lecturerDTO);
                lecturer.setCreatedAt(Instant.now());

                lecturerRepository.save(lecturer);

                lecturerDTO.setLecturerId(savedLecturer.get_id());
                savedLecturer = lecturer;
            }
            return savedLecturer;

        }catch (Exception e){
            return null;
        }

    }

    public Optional<Lecturer> getLecturerById(String lecturerId) {
        return lecturerRepository.findBy_id(lecturerId);
    }

    public boolean deleteLecturerById(String lecturerId) {
        try {
            // Check if the lecturer exists
            Optional<Lecturer> optionalLecturer = lecturerRepository.findBy_id(lecturerId);
            if (optionalLecturer.isPresent()) {
                // Delete the lecturer if it exists
                lecturerRepository.deleteBy_id(lecturerId);
                return true; // Deletion successful
            } else {
                // Lecturer not found
                return false;
            }
        } catch (Exception e) {
            // Handle exceptions, log errors, or return false if deletion fails
            return false;
        }
    }

    private Lecturer map(LecturerDTO dto){
        return Lecturer.builder().firstName(dto.getFirstName()).createdAt(Instant.now())
                .lastName(dto.getLastName()).email(dto.getEmail()).password(passwordEncoder(dto.getPassword())).type(dto.getType().toLowerCase()).user("lecturer").pass("bhtd123").build();
    }

    public boolean updateLecturer(LecturerDTO lecturerDTO) {
        try {
            Optional<Lecturer> existingLecturer = lecturerRepository.findBy_id(lecturerDTO.getLecturerId());
            if (existingLecturer.isPresent()) {
                Lecturer lecturerToUpdate = existingLecturer.get();
                // Update lecturer details based on DTO
                lecturerToUpdate.setFirstName(lecturerDTO.getFirstName());
                lecturerToUpdate.setLastName(lecturerDTO.getLastName());
                lecturerToUpdate.setEmail(lecturerDTO.getEmail());
                lecturerToUpdate.setType(lecturerDTO.getType());
                lecturerToUpdate.setPassword(passwordEncoder(lecturerDTO.getPassword()));
                // Save the updated lecturer
                lecturerRepository.save(lecturerToUpdate);
                return true; // Update successful
            } else {
                return false; // Lecturer not found
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return false; // Update failed
        }
    }


    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }
}
