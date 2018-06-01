package com.spring.presentation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spring.presentation.model.Student;


@Repository
@Transactional
public interface StudentDAO extends JpaRepository<Student, Long> {

    Student findStudentByEmail(String email);


        }