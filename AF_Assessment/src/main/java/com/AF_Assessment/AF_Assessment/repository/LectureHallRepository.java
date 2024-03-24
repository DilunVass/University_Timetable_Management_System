package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.LectureHall;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LectureHallRepository extends MongoRepository<LectureHall, ObjectId> {
    Optional<LectureHall> findLectureHallByHallCode(String code);

    Optional<LectureHall> findBy_id(String hallId);

    void deleteBy_id(String hallId);
}
