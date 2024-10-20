package com.example.postgresSpring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
// @Table(name = "T_Student")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(
        name = "Fname"
    )
    private String firstname;

    @Column(
        name = "Lname"
    )
    private String lastname;

    @Column(
        unique = true
    )
    private String email;

    private int age;

    @OneToOne(
        mappedBy = "student",
        cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;


    @ManyToOne
    @JoinColumn(
        name = "school_id"
    )
    @JsonBackReference
    private School school;
}
