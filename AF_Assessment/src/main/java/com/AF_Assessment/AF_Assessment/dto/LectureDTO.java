package com.AF_Assessment.AF_Assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private String id, lectureHall, lecturer, subject;
//    private String startTime;
    private int duration;
    private LocalDate date;
    private LocalTime startTime;
}
