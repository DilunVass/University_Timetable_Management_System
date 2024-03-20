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
import java.time.LocalDate;

@Document(collection = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Practical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int practicalId;

    @NotNull(message = "Start time is required")
    private LocalDate startTime;

    @NotNull(message = "End time is required")
    private LocalDate endTime;

    @NotNull(message = "Duration is required")
    @Range(min = 1, max = 10)
    private int duration;

    //ref lab

    //ref lecturer

    //ref subject
}
