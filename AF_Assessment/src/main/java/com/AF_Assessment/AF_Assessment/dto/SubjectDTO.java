package com.AF_Assessment.AF_Assessment.dto;


import com.AF_Assessment.AF_Assessment.model.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private int subjectId, credits;
    private String description, name,lecturerId;


}
