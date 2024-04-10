package com.AF_Assessment.AF_Assessment.dto;


import com.AF_Assessment.AF_Assessment.model.Subjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String id, firstName, lastName, email, password;
    private String[] subjects;

}
