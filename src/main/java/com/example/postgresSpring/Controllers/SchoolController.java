package com.example.postgresSpring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresSpring.Dto.SchoolDto;
import com.example.postgresSpring.DtoResponses.SchoolResponseDto;
import com.example.postgresSpring.Services.SchoolService;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolResponseDto createSchool(@RequestBody SchoolDto schoolDto){
        return schoolService.addNewSchool(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getAllSchools(){
        return schoolService.getListOfSchools();
    }

    
}
