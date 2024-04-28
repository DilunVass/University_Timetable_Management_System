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
@DisplayName("PracticalAPITests")
@Tag("Integration")
public class PracticalAPITests {

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
    void testFindAllPracticalsSuccess() throws Exception {

        // Perform GET request to the API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/api/v1/practicals")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    void testAddPracticalSuccess() throws Exception {

        String requestBody = "{\"date\": \"2024-06-13\", \"startTime\": \"13:30\", \"duration\": \"2\", \"subject\": \"SPM3\", \"lab\": \"A506\", \"lecturer\": \"dilun01@gmail.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/api/v1/addPractical")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePracticalSuccess() throws Exception {

        String requestBody = "{\"date\": \"2024-05-12\", \"startTime\": \"13:30\", \"duration\": \"2\", \"subject\": \"SPM3\", \"lab\": \"A506\", \"lecturer\": \"dilun01@gmail.com\"}";
        String practicalId = "65ff2cc20b6e046b1c230b6b";

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/api/v1/updatePractical/" + practicalId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePracticalSuccess() throws Exception {

        String practicalId = "";

        // Perform DELETE request to delete the lecturer
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/api/v1/deletePractical/" + practicalId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }
}
