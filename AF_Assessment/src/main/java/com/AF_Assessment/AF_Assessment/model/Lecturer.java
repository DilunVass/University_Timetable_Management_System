//package com.AF_Assessment.AF_Assessment.model;
//
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import java.time.Instant;
//import java.time.LocalDateTime;
//
//@Document(collection = "lecturers")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Lecturer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private int _id;
//
//    @NotEmpty(message = "First name is required")
//    private String firstName;
//
//    @NotEmpty(message = "Last name is required")
//    private String lastName;
//
//    @Column(unique = true)
//    @NotEmpty(message = "email is required")
//    @Email(message = "Invalid email type...")
//    private String email;
//
//    @NotEmpty(message = "Type is required")
//    private String type;
//
////    private Instant createdAt;
//
//
//    public Lecturer(String firstName, String lastName, String email, String type) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.type = type;
//
//    }
//}

package com.AF_Assessment.AF_Assessment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "lecturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lecturer {
    @Id
    private String _id; // Use String type for MongoDB ObjectId

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "email is required")
    @Email(message = "Invalid email type...")
    private String email;

    @NotEmpty(message = "Type is required")
    private String type;

    private Instant createdAt;

        public Lecturer(String firstName, String lastName, String email, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;

    }
}

