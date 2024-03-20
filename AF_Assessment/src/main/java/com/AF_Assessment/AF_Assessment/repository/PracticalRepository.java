package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Practical;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PracticalRepository extends MongoRepository<Practical, ObjectId> {
}
