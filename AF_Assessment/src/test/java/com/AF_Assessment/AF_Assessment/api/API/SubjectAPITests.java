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
@DisplayName("SubjectAPITests")
@Tag("Integration")
public class SubjectAPITests {

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
    void testFindAllSubjectSuccess() throws Exception {
        // Perform GET request to the API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/subjects")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));
    }


    @Test
    void testAddSubjectSuccess() throws Exception {

        String requestBody = "{\"name\": \"DMS\", \"credits\": \"3\", \"description\": \"This is SPM module\", \"lecturerId\": \"65fd783592ea3f1a0cef2aee\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addsubject")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    void testUpdateSubjectSuccess() throws Exception {

        String requestBody = "{\"name\": \"DMS\", \"credits\": \"4\", \"description\": \"This is SPM module updated\", \"lecturerId\": \"65fd783592ea3f1a0cef2aee\"}";

        String subjectId = "662cbbbe9a59103711a5bef1";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updateSubject/" + subjectId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }


    @Test
    void testDeleteSubjectSuccess() throws Exception {
        String subjectId = "";

        // Perform DELETE request to delete the subject
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/deleteSubject/" + subjectId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }



}
