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
    private int lectureId;

    @NotNull(message = "Start time is required")
    private LocalDate startTime;

    @NotNull(message = "End time is required")
    private LocalDate endTime;

    @NotNull(message = "Duration is required")
    @Range(min = 1, max = 10)
    private int duration;


    //lectureHall ref

    //ref lecturer


    @NotNull(message = "Subject is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjects", referencedColumnName = "subjectId")
    private Subjects subject;
}
