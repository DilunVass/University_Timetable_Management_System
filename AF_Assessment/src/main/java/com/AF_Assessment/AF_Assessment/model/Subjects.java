package com.AF_Assessment.AF_Assessment.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Instant;

@Document(collection = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subjects {
    @Id
    private String _id;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Credits is required")
    @Range(min= 0, max= 50)
    private int credits;

    @NotEmpty(message = "Subject name is required")
    private String name;

    @NotNull(message = "lecturer is required")
    private String lecturerId;

    private Instant createdAt;

}
