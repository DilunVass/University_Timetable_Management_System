package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Lab;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabRepository extends MongoRepository<Lab, ObjectId> {

}