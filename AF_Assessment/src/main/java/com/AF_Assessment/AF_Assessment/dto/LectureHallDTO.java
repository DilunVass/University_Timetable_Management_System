package com.AF_Assessment.AF_Assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureHallDTO {

    private String id, type, hallCode, description, createdAt;
}
