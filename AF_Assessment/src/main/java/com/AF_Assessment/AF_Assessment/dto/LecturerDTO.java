package com.AF_Assessment.AF_Assessment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerDTO {

    private int lecturerId;
    private String firstName, lastName, email, type;
}
