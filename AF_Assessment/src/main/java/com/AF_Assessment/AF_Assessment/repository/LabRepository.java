package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Lab;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LabRepository extends MongoRepository<Lab, ObjectId> {
    Optional<Lab> findLabByLabCode(String code);

    Optional<Lab> findBy_id(String labId);

    void deleteBy_id(String labId);
}
