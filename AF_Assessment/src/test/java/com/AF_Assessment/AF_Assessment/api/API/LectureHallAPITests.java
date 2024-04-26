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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("LectureHallAPITests")
@Tag("Integration")
public class LectureHallAPITests {

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
    void testFindAllLectureHallSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/lectureHalls")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testAddLectureHallSuccess() throws Exception {

        String requestBody = "{\"type\": \"Computer Science\", \"hallCode\": \"A709\", \"description\": \"This is main building property}\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addlecturehall")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLectureHallSuccess() throws Exception {
        String lectureHallId = "66211bb74622bf4d35cf219b";
        String requestBody = "{\"type\": \"Computer Science\", \"hallCode\": \"A709\", \"description\": \"This is main building property}\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updateLectureHall/" + lectureHallId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testDeleteLectureHallSuccess() throws Exception {
        String lectureHallId = "6621f90af1bc5902f128fec3";

        // Perform DELETE request to delete the lecture hall
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/deleteLectureHall/" + lectureHallId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

}
