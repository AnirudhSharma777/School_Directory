package com.example.postgresSpring.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresSpring.Dto.StudentDto;
import com.example.postgresSpring.DtoResponses.StudentResponseDto;
import com.example.postgresSpring.Entities.Student;
import com.example.postgresSpring.Services.StudentService;

@RestController
public class FirstController {

    private final StudentService studentService;
    
    public FirstController(StudentService studentService){
        this.studentService = studentService;
    }


    @PostMapping("/students")
    public StudentResponseDto postStudent(@RequestBody StudentDto studentDto){
       return studentService.createStudent(studentDto);
    }


    @GetMapping("/students/{student_id}")
    public Student getStudentById(@PathVariable("student_id") Long id){
        return  studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentService.getListOfStudent();
    }

    @GetMapping("/students/search/{student_name}")
    public Student getStudentByName(@PathVariable("student_name") String name){
        return  studentService.getStudentByThereName(name);
    }


    @GetMapping("/students/search_all/{student_name}")
    public List<Student> getAllStudentBySameName(@PathVariable("student_name") String name){
        return  studentService.getAllStudentByThereName(name);
    }


    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student_id") Long id){
        studentService.deleteStudentById(id);
    }


}
