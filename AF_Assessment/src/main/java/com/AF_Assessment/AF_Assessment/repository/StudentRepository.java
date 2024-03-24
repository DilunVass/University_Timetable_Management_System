package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findBy_id(String studentId);

    void deleteBy_id(String studentId);
}
