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
@DisplayName("LabAPITests")
@Tag("Integration")
public class LabAPITests {

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
    void testFindAllLabSuccess() throws Exception {
        // Perform GET request to the API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/labs")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }


    @Test
    void testAddLabSuccess() throws Exception {

        String requestBody = "{\"type\": \"Computer Science\", \"labCode\" : \"A508\", \"description\": \"This is main building property.\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addlab")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLabSuccess() throws Exception {

        String LabId = "65fe41f6c456905b5891a938";
        String requestBody = "{\"type\": \"Computer Science\", \"labCode\" : \"A506\", \"description\": \"This is main building property.\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updateLab/" + LabId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteLabSuccess() throws Exception {

        String LabId = "66219602c0d9df3dd6a539e7";

        // Perform DELETE request to delete the lecturer
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/lab/" + LabId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isNoContent());

    }

}
