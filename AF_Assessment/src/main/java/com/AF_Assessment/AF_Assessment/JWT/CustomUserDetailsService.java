package com.AF_Assessment.AF_Assessment.JWT;

import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Load student details from the database based on the provided email
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        // If student not found in the database, throw an exception
        if (!studentOptional.isPresent()) {
            throw new UsernameNotFoundException("Student not found with email: " + email);
        }

        // Get the student from Optional
        Student student = studentOptional.get();

        // Create UserDetails object from student details
        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword()) // Assuming password is stored securely (e.g., hashed)
                .authorities("ROLE_STUDENT") // Provide appropriate authorities
                .build();
    }
}