package com.meli.recruiter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class DnaControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @Test
    public void validateDnaMutantTest() throws Exception {
        String payload = "{ \n" +
                "    \"dna\":[\"AAGCGA\",\"CAGTGC\",\"TAATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                "}\n";
        String endPoint = "/api/mutant";

        MvcResult received = this.mockMVC.perform(MockMvcRequestBuilders.post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), received.getResponse().getStatus());
    }

    @Test
    public void validateDnaNotMutantTest() throws Exception {
        String payload = "{ \n" +
                "    \"dna\":[\"CAGCTA\",\"CAGTGC\",\"TAATGT\",\"AGAAGG\",\"CACCTA\",\"TCACTG\"]\n" +
                "}\n";
        String endPoint = "/api/mutant";

        MvcResult received = this.mockMVC.perform(MockMvcRequestBuilders.post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn();

        assertEquals(HttpStatus.FORBIDDEN.value(), received.getResponse().getStatus());
    }

    @Test
    public void validateStatsTest() throws Exception {
        String payload = "";
        String endPoint = "/api/stats";

        MvcResult received = this.mockMVC.perform(MockMvcRequestBuilders.get(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), received.getResponse().getStatus());
    }

}
