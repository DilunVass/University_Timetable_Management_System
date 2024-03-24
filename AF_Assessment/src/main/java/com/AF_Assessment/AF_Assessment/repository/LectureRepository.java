package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Lecture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LectureRepository extends MongoRepository<Lecture, ObjectId> {
    Optional<Lecture> findBy_id(String lectureId);

    void deleteBy_id(String lectureId);
}
