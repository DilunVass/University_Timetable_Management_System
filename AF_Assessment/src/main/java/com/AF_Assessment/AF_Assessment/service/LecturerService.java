package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.LecturerDTO;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import com.AF_Assessment.AF_Assessment.util.ExtraUtilities;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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


//    public Lecturer addLecturer(String firstName, String lastName, String email, String type){
//        Lecturer lecturer = lecturerRepository.insert(new Lecturer(firstName,lastName, email, type));
//
//        mongoTemplate.update(Lecturer.class)
//                .matching(Criteria.where("email").is(email))
//                .apply(new Update().push("id").value(lecturer.getId()))
//                .first();
//        return lecturer;
//    }

//    public Lecturer addLecturer(String firstName, String lastName, String email, String type) {
//        Lecturer lecturer = new Lecturer(firstName, lastName, email, type);
//        lecturer = lecturerRepository.insert(lecturer);
//
//        Query query = new Query(Criteria.where("email").is(email));
//        Update update = new Update().set("_id", lecturer.get_id());
//        mongoTemplate.updateFirst(query, update, Lecturer.class);
//
//        return lecturer;
//    }


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

                lecturerDTO.setLecturerId(Integer.parseInt(savedLecturer.get_id()));
                savedLecturer = lecturer;
            }
            return savedLecturer;

        }catch (Exception e){
            return null;
        }

    }

    private Lecturer map(LecturerDTO dto){
        return Lecturer.builder().firstName(dto.getFirstName()).createdAt(Instant.now())
                .lastName(dto.getLastName()).email(dto.getEmail()).type(dto.getType().toLowerCase()).build();
    }


    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }
}
