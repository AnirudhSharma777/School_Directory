package com.example.postgresSpring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresSpring.Entities.Student;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByFirstnameContaining(String firstname);

    List<Student> findAllByFirstnameContaining(String firstname);
}
