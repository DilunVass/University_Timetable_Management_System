package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Practical;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PracticalRepository extends MongoRepository<Practical, ObjectId> {
    Optional<Practical> findBy_id(String id);
    Optional<Practical> findBySubject(String subject);

    void deleteBy_id(String id);
}
