package com.example.postgresSpring.Dto;


import jakarta.validation.constraints.NotNull;

public record StudentDto(

    @NotNull
    String firstname,

    @NotNull
    String lastname,

    String email,
    Long school_id
) {

}
