package com.AF_Assessment.AF_Assessment.model;

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
import java.time.LocalDate;

@Document(collection = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Practical {
    @Id
    private String _id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private String startTime;

//    @NotNull(message = "End time is required")
//    private LocalDate endTime;

    @NotNull(message = "Duration is required")
    @Range(min = 1, max = 10)
    private int duration;

    //ref lab
    @NotNull(message = "Lab is required")
    private String lab;

    //ref lecturer
    @NotNull(message = "lecturer is required")
    private String lecturer;

    //ref subject
    @NotNull(message = "subject is required")
    private String subject;

    Instant updatedAt;
}
