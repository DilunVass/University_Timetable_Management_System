package com.AF_Assessment.AF_Assessment.api.API;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("StudentAPITests")
@Tag("Integration")
public class StudentAPITest {

    @Autowired
    MockMvc mockMvc;

    private String token;

    @Value("http://localhost:8080")
    String baseUrl;

    @BeforeEach
    void setUp() throws Exception {
        String requestBody = "{\"username\": \"diun@gmail.com\", \"password\": \"abc\"}";
        // Perform a POST request to obtain the token
        this.token = mockMvc.perform(MockMvcRequestBuilders.post(this.baseUrl + "/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void testFindAllStudentSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/students")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    void testAddStudentSuccess() throws Exception {

        String requestBody = "{" +
                "\"firstName\": \"Dilun\"," +
                "\"lastName\": \"Ransinshe\"," +
                "\"email\": \"dilunvass2001@gmail.com\"," +
                "\"password\": \"xyz21abdn\"," +
                "\"subjects\": [\"65fd874eed0ee430e781109f\",\"65fd8754ed0ee430e78110a0\"]" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addstudent")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }


    @Test
    void testUpdateStudentSuccess() throws Exception {

        String requestBody = "{" +
                "\"firstName\": \"Dilun\"," +
                "\"lastName\": \"Nishalka\"," +
                "\"email\": \"dilunvass2001@gmail.com\"," +
                "\"subjects\": [\"65fd874eed0ee430e781109f\",\"65fd8754ed0ee430e78110a0\"]" +
                "}";

        String studentId = "662cfed256d09501538ac432";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updateStudent/" + studentId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteStudentSuccess() throws Exception {

        String studentId = "";

        // Perform DELETE request to delete the student
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/deleteStudent/" + studentId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }
}
