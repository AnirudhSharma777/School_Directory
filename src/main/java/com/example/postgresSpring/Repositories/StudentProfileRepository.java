package com.example.postgresSpring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresSpring.Entities.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long>{

}
