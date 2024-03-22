package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Subjects;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubjectsRepository extends MongoRepository<Subjects, ObjectId> {

    Optional<Subjects> findByName(String subjectName);

}
