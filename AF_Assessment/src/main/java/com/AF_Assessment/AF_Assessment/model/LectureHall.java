package com.AF_Assessment.AF_Assessment.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Document(collection = "lectureHalls")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureHall {
    @Id
    private String _id;

    @NotEmpty(message = "Lecture Hall type is required")
    private String type;

    @NotEmpty(message = "Lecture Hall code is required")
    private String hallCode;

    @NotEmpty(message = "Description is required")
    private String description;

    Instant createdAt;

    Instant updatedAt;

}
