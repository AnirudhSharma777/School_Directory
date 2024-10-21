package com.example.postgresSpring.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.postgresSpring.Dto.StudentDto;
import com.example.postgresSpring.DtoResponses.StudentResponseDto;
import com.example.postgresSpring.Entities.Student;
import com.example.postgresSpring.Repositories.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        // Given
        StudentDto dto = new StudentDto("John", "Doe", "john.doe@gmail.com", 1L);

        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmail("john.doe@gmail.com");
        student.setAge(20);

        // Mock the calls
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        StudentResponseDto response = studentService.createStudent(dto);

        // Then
        // assertNotNull(response);
        assertEquals(dto.firstname(), response.firstname());
        assertEquals(dto.lastname(), response.lastname());
        assertEquals(dto.email(), response.email());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testDeleteStudentById() {

        Long studentId = 1L;

        // When
        studentService.deleteStudentById(studentId);

        // Then
        verify(studentRepository,times(1)).deleteById(studentId);
    }

    @Test
    void testGetAllStudentByThereName() {

        // Given
        String name = "John";
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setFirstname("John");
        student1.setLastname("Doe");
        student1.setEmail("john.doe@gmail.com");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setFirstname("Jane");
        student2.setLastname("Smith");
        student2.setEmail("jane.smith@gmail.com");
        student2.setAge(22);

        Student student3 = new Student();
        student3.setFirstname("John");
        student3.setLastname("Smith");
        student3.setEmail("john@gmail.com");
        student3.setAge(20);

        students.add(student3);
        students.add(student1);
        students.add(student2);

        // Mock the calls
        when(studentRepository.findAllByFirstnameContaining(name)).thenReturn(students);

        // when
        List<StudentResponseDto> response = studentService.getAllStudentByThereName(name);

        // then
        assertNotNull(response);
        assertEquals(3, response.size());
        assertEquals("John", response.get(0).firstname());

        verify(studentRepository, times(1)).findAllByFirstnameContaining(name);

    }

    @Test
    void testGetListOfStudent() {

        // Given
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setFirstname("John");
        student1.setLastname("Doe");
        student1.setEmail("john.doe@gmail.com");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setFirstname("Jane");
        student2.setLastname("Smith");
        student2.setEmail("jane.smith@gmail.com");
        student2.setAge(22);

        students.add(student1);
        students.add(student2);

        // Mock the calls
        Mockito.when(studentRepository.findAll()).thenReturn(students);

        // When
        List<StudentResponseDto> response = studentService.getListOfStudent();

        // then

        assertEquals(2, response.size());
        assertEquals("John", response.get(0).firstname());
        assertEquals("Jane", response.get(1).firstname());

        Mockito.verify(studentRepository, times(1)).findAll();

    }

    @Test
    void testGetStudentById() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmail("john.doe@gmail.com");

        // Mock the calls
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // When
        StudentResponseDto response = studentService.getStudentById(studentId);

        // Then
        assertNotNull(response);
        assertEquals("John", response.firstname());
        assertEquals("Doe", response.lastname());
        assertEquals("john.doe@gmail.com", response.email());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testGetStudentByThereName() {

        // Given
        String name = "John";
        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmail("john.doe@gmail.com");
        student.setAge(20);

        // Mock the calls
        Mockito.when(studentRepository.findByFirstnameContaining(name)).thenReturn(student);

        // When
        StudentResponseDto response = studentService.getStudentByThereName(name);

        // Then
        assertNotNull(response);
        assertEquals("John", response.firstname());

        verify(studentRepository, times(1)).findByFirstnameContaining(name);

    }
}
