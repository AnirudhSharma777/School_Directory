package com.example.postgresSpring.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue
    private Long id;

    private String schoolName;

    @OneToMany(
        mappedBy = "school" 
        // cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Student> students;
}
