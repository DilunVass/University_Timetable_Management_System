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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("LecturerAPITests")
@Tag("Integration")
public class LecturerAPITests {

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
    void testFindAllLecturerSuccess() throws Exception {
        // Perform GET request to the API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/lecturers")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$").isArray())

                .andExpect(jsonPath("$[0]").exists())

                .andExpect(jsonPath("$[0].firstName").value("dlun3"))
                .andExpect(jsonPath("$[0].lastName").value("vss3"))
                .andExpect(jsonPath("$[0].email").value("diun4@gmail.com"))
                .andExpect(jsonPath("$[0].type").value("visiting"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testAddLecturerSuccess() throws Exception {
        // Define the request body for adding a new lecturer
        String requestBody = "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"johndoe2@example.com\", \"type\": \"permanent\"}";


        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addlecturer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    void testDeleteLecturerSuccess() throws Exception {

        String lecturerId = "65fd783592ea3f1a0cef2aee";

        // Perform DELETE request to delete the lecturer
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/lecturerDelete/" + lecturerId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLecturerSuccess() throws Exception {

        String lecturerId = "6620d12740c473144ee00a02";
        String requestBody = "{\"firstName\": \"dlunvass\", \"lastName\": \"vss3\", \"email\": \"diun04@gmail.com\", \"password\": \"abc\", \"type\": \"Visiting\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updateLecturer/" + lecturerId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testDeleteLectureSuccess() throws Exception {
        String lectureId = "";

        // Perform DELETE request to delete the lecturer
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/deleteLecture/" + lectureId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

}

