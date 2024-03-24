package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.StudentDTO;
import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import com.AF_Assessment.AF_Assessment.util.ExtraUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDTO dto){
        try{
            Optional<Student> existing = studentRepository.findStudentByEmail(dto.getEmail());

            Student savedStudent = null;

            if (existing.isPresent()){
                throw new IllegalArgumentException("Email already exists");
            }else {
                if (!ExtraUtilities.isEmailValid(dto.getEmail())) {
                    throw new IllegalArgumentException("Invalid email");
                }else {

                    if (subjectCheck(dto) == 1){
                        Student student = map(dto);
                        student.setCreatedAt(Instant.now());
                        studentRepository.save(student);

                        dto.setId(savedStudent.get_id());
                        return savedStudent;
                    }

                }
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }

    public Student updateStudent(String studentId, StudentDTO updatedStudentDTO) {
        // Retrieve the existing student from the database
        Optional<Student> existingStudentOptional = studentRepository.findBy_id(studentId);

        if (existingStudentOptional.isPresent()) {
            // Student exists, update its fields
            Student existingStudent = existingStudentOptional.get();

            // Update fields if provided in the DTO
            if (updatedStudentDTO.getFirstName() != null) {
                existingStudent.setFirstName(updatedStudentDTO.getFirstName());
            }
            if (updatedStudentDTO.getLastName() != null) {
                existingStudent.setLastName(updatedStudentDTO.getLastName());
            }
            if (updatedStudentDTO.getEmail() != null) {
                // Validate and update email
                if (ExtraUtilities.isEmailValid(updatedStudentDTO.getEmail())) {
                    existingStudent.setEmail(updatedStudentDTO.getEmail());
                } else {
                    throw new IllegalArgumentException("Invalid email");
                }
            }
            if (updatedStudentDTO.getPassword() != null) {
                existingStudent.setPassword(updatedStudentDTO.getPassword());
            }
            // Optionally, update other fields as needed

            // Set the updated timestamp
            existingStudent.setUpdatedAt(Instant.now());

            // Save the updated student back to the database
            return studentRepository.save(existingStudent);
        } else {
            // Student with the given ID not found
            throw new IllegalArgumentException("Student not found");
        }
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteBy_id(studentId);
    }


    private Student map(StudentDTO dto){
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .subjects(dto.getSubjects())
                .createdAt(Instant.now())
                .build();
    }

    private int subjectCheck(StudentDTO dto){
        String temp = null;
        int test=0;
        for (int i=0; i<dto.getSubjects().length; i++){
            temp = dto.getSubjects()[i];
            Optional<Subjects> ex = subjectsRepository.findBy_id(temp);
            if (ex.isPresent()){
                test = test+1;
            }
        }

        if (test == dto.getSubjects().length){
            return 1;
        }
        else {
            return -1;
        }
    }
}
