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
import java.time.LocalTime;

@Document(collection = "lectures")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lecture {
    @Id
    private String _id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private String startTime;

    @NotNull(message = "End time is required")
    private int duration;

    //lectureHall ref
    @NotNull(message = "lectureHall is required")
    private String lectureHall;

    //ref lecturer
    @NotNull(message = "lecturer is required")
    private String lecturer;

    //ref subject
    @NotNull(message = "Subject is required")
    private String subject;

}
