package com.example.postgresSpring.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.postgresSpring.Dto.SchoolDto;
import com.example.postgresSpring.DtoResponses.SchoolResponseDto;
import com.example.postgresSpring.Entities.School;
import com.example.postgresSpring.Repositories.SchoolRepository;


// @ExtendWith(MockitoExtension.class)
public class SchoolServiceTest {

    @InjectMocks
    private SchoolService schoolService;

    @Mock
    private SchoolRepository schoolRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewSchool() {

        // Given
        SchoolDto schoolDto = new SchoolDto("Greenfield Academy");

        School school = new School();
        school.setSchoolName(schoolDto.schoolName());

        // Mock the calls
        when(schoolRepository.save(any(School.class))).thenReturn(school);

        // When
        SchoolResponseDto response = schoolService.addNewSchool(schoolDto);

        // Then
        assertNotNull(response);
        assertEquals("Greenfield Academy", response.schoolName());

        verify(schoolRepository, times(1)).save(any(School.class));

    }

    @Test
    void testGetListOfSchools() {

        // Given
        List<School> schools = new ArrayList<>();

        School school1 = new School();
        school1.setSchoolName("Greenfield Academy");

        School school2 = new School();
        school2.setSchoolName("Blue Ridge High School");

        schools.add(school1);
        schools.add(school2);

        // Mock the calls
        when(schoolRepository.findAll()).thenReturn(schools);

        // When
        List<SchoolResponseDto> responseList = schoolService.getListOfSchools();

        // Then
        assertNotNull(responseList);
        // assertEquals(2, responseList.size());
        assertEquals("Greenfield Academy", responseList.get(0).schoolName());
        assertEquals("Blue Ridge High School", responseList.get(1).schoolName());

        verify(schoolRepository,times(1)).findAll();

    }
}
