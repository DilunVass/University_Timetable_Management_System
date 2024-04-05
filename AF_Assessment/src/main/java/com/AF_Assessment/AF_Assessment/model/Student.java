package com.AF_Assessment.AF_Assessment.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Column;
import java.time.Instant;

@Document(collection = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    private String _id;

    @Column(unique = true)
    @NotEmpty(message = "email is required")
    @Email(message = "Invalid email type...")
    @Indexed
    private String email;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Password is required")
    private String password;

    private Instant createdAt;

    private Instant updatedAt;

    //ref
    private String[] subjects;
}
