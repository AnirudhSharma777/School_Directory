package com.example.postgresSpring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresSpring.Entities.School;

public interface SchoolRepository extends JpaRepository<School,Long>{

}
