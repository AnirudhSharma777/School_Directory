package com.example.postgresSpring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresSpring.Entities.School;
import com.example.postgresSpring.Repositories.SchoolRepository;

@RestController
public class SchoolController {


    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public School createSchool(@RequestBody School school){
        return schoolRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> getAllSchools(){
        return schoolRepository.findAll();
    }

    
}
