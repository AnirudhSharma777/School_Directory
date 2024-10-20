package com.example.postgresSpring.Services;

import java.util.List;


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

    private Student mapper(StudentDto dto) {
        Student student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.school_id());

        student.setSchool(school);

        return student;
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    public List<Student> getListOfStudent() {
       return studentRepository.findAll();
    }

    public Student getStudentByThereName(String name) {
       return studentRepository.findByFirstnameContaining(name);
    }

    public List<Student> getAllStudentByThereName(String name) {
        return studentRepository.findAllByFirstnameContaining(name);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

   

}
