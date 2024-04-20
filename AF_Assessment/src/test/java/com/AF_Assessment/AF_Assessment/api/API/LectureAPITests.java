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
@DisplayName("LectureAPITests")
@Tag("Integration")
public class LectureAPITests {

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
    void testFindAllLecturesSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/lectures")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    void testAddLectureSuccess() throws Exception {
        String requestBody = "{\"date\" : \"2024-05-12\", \"startTime\": \"13:30\", \"duration\" : \"5\", \"subject\" : \"SPM\", \"lectureHall\": \"A503\", \"lecturer\": \"dilun@gmail.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addLecture")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLecture() throws Exception {
        
    }
}
