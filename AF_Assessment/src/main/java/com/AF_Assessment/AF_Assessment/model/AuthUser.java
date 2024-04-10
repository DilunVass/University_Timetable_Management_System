package com.AF_Assessment.AF_Assessment.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("user")
public class AuthUser {
    @Id
    private String _id;
    @Indexed
    private String username;
    private String password;
    private boolean active;
}
