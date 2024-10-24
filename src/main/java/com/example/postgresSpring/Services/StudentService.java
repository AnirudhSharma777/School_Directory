package com.example.postgresSpring.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.postgresSpring.Dto.StudentDto;
import com.example.postgresSpring.DtoResponses.StudentResponseDto;
import com.example.postgresSpring.Entities.School;
import com.example.postgresSpring.Entities.Student;
import com.example.postgresSpring.Repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDto createStudent(StudentDto studentDto) {
        Student student = mapper(studentDto);
        Student savedStudent = studentRepository.save(student);
        return new StudentResponseDto(
                savedStudent.getFirstname(),
                savedStudent.getLastname(),
                savedStudent.getEmail());
    }

    public Student mapper(StudentDto dto) {
        Student student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.school_id());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto getStudentById(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail());

    }

    public List<StudentResponseDto> getListOfStudent() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentResponseDto(
                        student.getFirstname(),
                        student.getLastname(),
                        student.getEmail()))
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudentByThereName(String name) {
        var student = studentRepository.findByFirstnameContaining(name);
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }

    // public List<StudentResponseDto> getAllStudentByThereName(String name) {
    //     return studentRepository.findAllByFirstnameContaining(name).stream()
    //             .map(student -> new StudentResponseDto(student.getFirstname(), student.getLastname(),
    //                     student.getEmail()))
    //             .collect(Collectors.toList());
    // }

    public List<StudentResponseDto> getAllStudentByThereName(String name) {
        List<Student> students = studentRepository.findAllByFirstnameContaining(name);
        return students.stream()
                       .map(student -> new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail()))
                       .toList();
    }
    

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
