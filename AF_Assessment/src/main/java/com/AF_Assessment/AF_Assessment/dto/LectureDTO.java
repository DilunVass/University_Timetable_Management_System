package com.AF_Assessment.AF_Assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private String id, lectureHall, lecture, subject;
    private LocalDate startTime, EndTime;
}
