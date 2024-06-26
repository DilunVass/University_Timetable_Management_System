package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.StudentDTO;
import com.AF_Assessment.AF_Assessment.model.Lecture;
import com.AF_Assessment.AF_Assessment.model.Practical;
import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.repository.LectureRepository;
import com.AF_Assessment.AF_Assessment.repository.PracticalRepository;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import com.AF_Assessment.AF_Assessment.util.ExtraUtilities;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private PracticalRepository practicalRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService senderService;


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity addStudent(StudentDTO dto){
        try{
            Optional<Student> existing = studentRepository.findStudentByEmail(dto.getEmail());

            Student savedStudent = null;

            if (existing.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            }else {
                if (!ExtraUtilities.isEmailValid(dto.getEmail())) {
                    throw new IllegalArgumentException("Invalid email");
                }else {

                    if (subjectCheck(dto) == 1){
                        Student student = map(dto);
                        student.setCreatedAt(Instant.now());
                        studentRepository.save(student);

                        dto.setId(savedStudent.get_id());
                        return ResponseEntity.ok(HttpStatus.CREATED);
                    }

                }
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
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

    public ResponseEntity getStudentLectures(String studentId){
        try {
            Optional<Student> student = studentRepository.findBy_id(studentId);
            Student stud = student.get();

            String[] subjects;
            String[] sub;
            String EmailSubject;
            String EmailBody;

            if (student.isPresent()){

                subjects = stud.getSubjects();

                int i=0;

                for (i=0; i<subjects.length; i++){
                    Optional<Subjects> s = subjectsRepository.findBy_id(subjects[i]);
                    if (s.isPresent()){
                        Subjects tempSub = s.get();

                        Optional<Lecture> lecture = lectureRepository.findBySubject(tempSub.getName());

                        if (lecture.isPresent()){
                            Lecture tempLecture = lecture.get();
                            EmailBody = tempLecture.getSubject() + "You having lecture on " + tempLecture.getDate();
                            EmailSubject = "Subject :- " + tempLecture.getSubject() + "\n" + "Started at :- " +tempLecture.getStartTime() + "\n" + "Lecture Hall :- " + tempLecture.getLectureHall() + "\n" + "Duration :- " + tempLecture.getDuration() + "\n" + "Lecturer :- " + tempLecture.getLecturer();
                            triggerMail(stud.getEmail(), EmailBody, EmailSubject);
                        }

                    }

                }

            }

            return null;
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity getStudentPracticals(String studentId) {
        try {
            Optional<Student> student = studentRepository.findBy_id(studentId);

            Student st = student.get();
            String[] subject;
            String emailSubject;
            String emailBody;

            if (student.isPresent()){
                subject = st.getSubjects();

                int i;

                for (i=0; i<subject.length; i++){
                    Optional<Subjects> s = subjectsRepository.findBy_id(subject[i]);

                    if (s.isPresent()){
                        Subjects tempSub = s.get();

                        Optional<Practical> p = practicalRepository.findBySubject(tempSub.getName());

                        if (p.isPresent()){
                            Practical tempPractical = p.get();

                            emailBody = tempPractical.getSubject() + "You having practical on " + tempPractical.getDate();
                            emailSubject = "Subject :- " + tempPractical.getSubject() + "\n" + "Started at :- " +tempPractical.getStartTime() + "\n" + "Lecture Hall :- " + tempPractical.getLab() + "\n" + "Duration :- " + tempPractical.getDuration() + "\n" + "Lecturer :- " + tempPractical.getLecturer();
                            triggerMail(st.getEmail(), emailBody, emailSubject);
                        }


                    }
                }
            }

            return null;
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



    private void triggerMail(String email, String subject, String body) throws MessagingException {
        senderService.sendSimpleEmail(email, subject, body);

    }

    private String passwordEncoder(String pass){
        return passwordEncoder.encode(pass);
    }

    private Student map(StudentDTO dto){
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder(dto.getPassword()))
                .subjects(dto.getSubjects())
                .user("student")
                .pass("bhtd123")
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
