package com.AF_Assessment.AF_Assessment.repository;

import com.AF_Assessment.AF_Assessment.model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String email);
}
