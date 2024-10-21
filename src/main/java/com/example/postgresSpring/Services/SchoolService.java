package com.example.postgresSpring.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.postgresSpring.Dto.SchoolDto;
import com.example.postgresSpring.DtoResponses.SchoolResponseDto;
import com.example.postgresSpring.Entities.School;
import com.example.postgresSpring.Repositories.SchoolRepository;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public SchoolResponseDto addNewSchool(SchoolDto schoolDto) {
        var school = new School();
        school.setSchoolName(schoolDto.schoolName());

        var schoolOption = schoolRepository.save(school);

        return new SchoolResponseDto(schoolOption.getSchoolName());

    }

    public List<SchoolResponseDto> getListOfSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(school -> new SchoolResponseDto(school.getSchoolName()))
                .collect(Collectors.toList());
    }

}
