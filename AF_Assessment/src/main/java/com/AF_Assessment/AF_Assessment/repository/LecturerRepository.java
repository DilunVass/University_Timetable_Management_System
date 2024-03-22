package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LecturerRepository extends MongoRepository<Lecturer, ObjectId> {
    Optional<Lecturer> findLecturerByEmail(String email);

    Optional<Lecturer> findBy_id(String id);

}
