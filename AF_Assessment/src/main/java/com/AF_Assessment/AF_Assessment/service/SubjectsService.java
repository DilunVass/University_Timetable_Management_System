package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.SubjectDTO;
//import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectsService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Subjects addSubject(SubjectDTO subjectDTO) {
        try {
            Subjects saveSubject = null;
            Optional<Subjects> existing = subjectsRepository.findByName(subjectDTO.getName());
            Optional<Lecturer> optionalLecturer = lecturerRepository.findBy_id(subjectDTO.getLecturerId());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("Subject already exists");
            }else {
                if (subjectDTO.getCredits() < 0 || subjectDTO.getCredits() > 50) {
                    throw new IllegalArgumentException("Invalid credit amount");
                }else {
                    if (optionalLecturer.isPresent()){
                        Subjects subjects = map(subjectDTO);
                        subjects.setCreatedAt(Instant.now());
                        subjectsRepository.save(subjects);

                        subjectDTO.setSubjectId(Integer.parseInt(saveSubject.get_id()));


                        return saveSubject;
                    }else {
                        throw new IllegalArgumentException("Lecturer is not exists");
                    }



                }
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (logging, error response, etc.)
            return null;
        }
    }


    public Subjects updateSubject(String subjectId, SubjectDTO updatedSubjectDTO) {
        // Find the subject by ID
        Optional<Subjects> optionalSubject = subjectsRepository.findBy_id(subjectId);

        // Check if the subject exists
        if (optionalSubject.isPresent()) {
            Subjects existingSubject = optionalSubject.get();

            // Update the subject fields with new values
            existingSubject.setName(updatedSubjectDTO.getName());
            existingSubject.setCredits(updatedSubjectDTO.getCredits());
            existingSubject.setDescription(updatedSubjectDTO.getDescription());
            existingSubject.setLecturerId(updatedSubjectDTO.getLecturerId());
            // Assuming other fields are also updated

            // Save the updated subject
            Subjects updatedSubject = subjectsRepository.save(existingSubject);

            return updatedSubject;
        } else {
            // If the subject doesn't exist, return null or handle the case accordingly
            return null;
        }
    }

    public boolean deleteSubject(String subjectId) {
        try {
            // Check if the subject exists
            if (subjectsRepository.existsBy_id(subjectId)) {
                // If the subject exists, delete it
                subjectsRepository.deleteBy_id(subjectId);
                return true; // Return true to indicate successful deletion
            } else {
                // If the subject doesn't exist, return false
                return false;
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., database errors)
            return false;
        }
    }

    private Subjects map(SubjectDTO subjectDTO) {
        return Subjects.builder()
                .name(subjectDTO.getName())
                .credits(subjectDTO.getCredits())
                .description(subjectDTO.getDescription())
                .createdAt(Instant.now())
                .lecturerId(subjectDTO.getLecturerId())
                .build();
    }



    public List<Subjects> getAllSubjects(){
        return subjectsRepository.findAll();
    }
}
