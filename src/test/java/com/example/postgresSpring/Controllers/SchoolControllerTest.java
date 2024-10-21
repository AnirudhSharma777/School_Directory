package com.example.postgresSpring.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.postgresSpring.Dto.SchoolDto;
import com.example.postgresSpring.DtoResponses.SchoolResponseDto;
import com.example.postgresSpring.Services.SchoolService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = SchoolController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSchool() throws Exception {
        // Given
        SchoolDto schoolDto = new SchoolDto("Greenwood International School");
        SchoolResponseDto responseDto = new SchoolResponseDto("Greenwood International School");

        when(schoolService.addNewSchool(any(SchoolDto.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(schoolDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolName").value("Greenwood International School"));

        verify(schoolService, times(1)).addNewSchool(any(SchoolDto.class));
    }

    @Test
    void testGetAllSchools() throws Exception {

        // Given
        List<SchoolResponseDto> schools = List.of(
                new SchoolResponseDto("Greenwood International School"),
                new SchoolResponseDto("Springfield High School"));

        when(schoolService.getListOfSchools()).thenReturn(schools);

        // When & Then
        mockMvc.perform(get("/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].schoolName").value("Greenwood International School"))
                .andExpect(jsonPath("$[1].schoolName").value("Springfield High School"));

        verify(schoolService, times(1)).getListOfSchools();
    }
}
