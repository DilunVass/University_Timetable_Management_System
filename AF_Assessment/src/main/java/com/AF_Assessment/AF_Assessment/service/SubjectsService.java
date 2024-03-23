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

//            String lectureId = subjectDTO.getLecturerId();
//            Lecturer lecturer = lecturerRepository.findById(new Lecturer())
//                    .orElseThrow(() -> new IllegalArgumentException("Lecturer not found"));
//
//            if (lecturerOptional.isEmpty()) {
//                throw new IllegalArgumentException("Lecturer not found");
//            }
//
//            Lecturer lecturer = lecturerOptional.get();


        } catch (Exception e) {
            // Handle exceptions appropriately (logging, error response, etc.)
            return null;
        }
    }

//    public SubjectDTO addSubject(SubjectDTO dto) {
//        try{
//            Optional<Subjects> existing = subjectsRepository.findByName(dto.getName());
//
//            if (existing.isPresent()){
//                throw new IllegalArgumentException("Subject already exists");
//            }else {
//                Lecturer lecturer = lecturerRepository.findBy_id(dto.getLecturerId()).orElseThrow(()-> new IllegalArgumentException("Lecturer not found"));
//
//                if (dto.getCredits() < 0 || dto.getCredits() > 50) {
//                  throw new IllegalArgumentException("Invalid credit amount");
//               }else {
//                    Subjects subjects = map(dto, String.valueOf(lecturer.get_id()));
//
//                    subjectsRepository.save(subjects);
//
//                    dto.setLecturerId(String.valueOf(lecturer));
//                    dto.setSubjectId(subjects.getId());
//                }
//
//            }
//        }catch (Exception  e){
//            return null;
//        }
//
//        return null;
//    }

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
