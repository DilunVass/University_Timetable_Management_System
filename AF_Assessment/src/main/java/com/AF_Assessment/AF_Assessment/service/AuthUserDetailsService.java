package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.model.AuthUser;
import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.repository.AuthUserRepository;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthUserRepository userRepository;

    private final StudentRepository studentRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<AuthUser> authUser = userRepository.findByUsername(username.toLowerCase());
//        if (!authUser.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        } else {
//            return User.builder()
//                    .username(authUser.get().getUsername())
//                    .password(authUser.get().getPassword())
//                    .disabled(!authUser.get().isActive())
//                    .build();
//        }
//    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (!student.isPresent()){
            throw new UsernameNotFoundException(email);
        }else {
            return (UserDetails) Student.builder()
                    .email(student.get().getEmail())
                    .password(student.get().getPassword())
                    .build();
        }
    }

}
