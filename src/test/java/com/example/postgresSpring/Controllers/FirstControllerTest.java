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

import com.example.postgresSpring.Dto.StudentDto;
import com.example.postgresSpring.DtoResponses.StudentResponseDto;
import com.example.postgresSpring.Services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = FirstController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FirstControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostStudent() throws Exception {
        // Given
        StudentDto studentDto = new StudentDto("John", "Doe", "john.doe@gmail.com", 1L);
        StudentResponseDto responseDto = new StudentResponseDto("John", "Doe", "john.doe@gmail.com");

        when(studentService.createStudent(any(StudentDto.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@gmail.com"));

        verify(studentService, times(1)).createStudent(any(StudentDto.class));
    }

    @Test
    void testDeleteStudent() throws Exception {

        // Given
    
        // When & Then
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudentById(1L);
    }

    @Test
    void testGetAllStudent() throws Exception {

        List<StudentResponseDto> students = List.of(
                new StudentResponseDto("John", "Doe", "john.doe@gmail.com"),
                new StudentResponseDto("Jane", "Smith", "jane.smith@gmail.com"));

        when(studentService.getListOfStudent()).thenReturn(students);

        // When & Then
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[1].firstname").value("Jane"));

        verify(studentService, times(1)).getListOfStudent();
    }

    @Test
    void testGetAllStudentBySameName() throws Exception {

        // Given
        List<StudentResponseDto> students = List.of(
                new StudentResponseDto("John", "Doe", "john.doe@gmail.com"),
                new StudentResponseDto("Johnny", "Doe", "johnny.doe@gmail.com"));

        when(studentService.getAllStudentByThereName("John")).thenReturn(students);

        // When & Then
        mockMvc.perform(get("/students/search_all/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[1].firstname").value("Johnny"));

        verify(studentService, times(1)).getAllStudentByThereName("John");

    }

    @Test
    void testGetStudentById() throws Exception{

        // Given
        StudentResponseDto responseDto = new StudentResponseDto("John", "Doe", "john.doe@gmail.com");
        when(studentService.getStudentById(1L)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@gmail.com"));

        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testGetStudentByName() throws Exception {
        // Given
        StudentResponseDto responseDto = new StudentResponseDto("John", "Doe", "john.doe@gmail.com");
        when(studentService.getStudentByThereName("John")).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/students/search/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));

        verify(studentService, times(1)).getStudentByThereName("John");
    }

}
