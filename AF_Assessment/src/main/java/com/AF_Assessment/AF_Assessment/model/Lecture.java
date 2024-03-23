package com.AF_Assessment.AF_Assessment.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Document(collection = "lectures")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lecture {
    @Id
    private String _id;

    @NotNull(message = "Start time is required")
    private LocalDate startTime;

    @NotNull(message = "End time is required")
    private LocalDate endTime;

    //lectureHall ref
    @NotNull(message = "lectureHall is required")
    private String lectureHall;

    //ref lecturer
    @NotNull(message = "lecture is required")
    private String lecture;

    //ref subject
    @NotNull(message = "Subject is required")
    private String subject;
}
