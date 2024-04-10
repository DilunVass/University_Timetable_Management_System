package com.AF_Assessment.AF_Assessment.controller;

import com.AF_Assessment.AF_Assessment.dto.LoginDTO;
import com.AF_Assessment.AF_Assessment.model.Student;
import com.AF_Assessment.AF_Assessment.repository.StudentRepository;
import com.AF_Assessment.AF_Assessment.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private StudentRepository studentRepository;

    private String username = "dilun";

    @Autowired
    private com.AF_Assessment.AF_Assessment.JWT.config config;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @PostMapping("/token")
    public String login(@RequestBody LoginDTO loginRequest) throws AuthenticationException{
        Optional<Student> ex = studentRepository.findStudentByEmail(loginRequest.getUsername());
        String token = "";
        Student student;
        String feedback = "";
        //String logUsername = loginRequest.getUsername();

        if (ex.isPresent()){
            student = ex.get();
           // feedback = "login username : " + loginRequest.getUsername() + "\nlogin password : " + loginRequest.getPassword() + "\ntrue username : " + student.getEmail() + "\ntrue password : " + student.getPassword();


            if (loginRequest.getUsername().equals(student.getEmail())){

                if (passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())){
                    token = token(student.getUser(), student.getPass());
                    feedback = "User :" + student.getFirstName() + " is logged";
                }else {
                    token = "user password is incorrect...";
                }

               // feedback = "login username : " + loginRequest.getUsername() + "\nlogin password : " + loginRequest.getPassword() + "\ntrue username : " + student.getEmail() + "\ntrue password : " + student.getPassword();
            }else {
                token = "Login user Email is incorrect....";
            }
        }
        return feedback + "\n" + token;
    }





    public String token(String username, String pass) throws AuthenticationException {
        // Authenticate student using provided credentials
//        Student student = ex.get();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, pass)
        );

        // Generate token for authenticated student
        return tokenService.generateToken(authentication);
    }
}

