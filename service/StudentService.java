package com.spring.presentation.service;

import com.spring.presentation.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {


    List<Student> getAllStudents();

    Student getStudentById(Long studentId);

    Student findStudentByEmail(String email);

    String logIn(String email, String passwd);

    Student changePassword(String email, String passwd, String newpasswd);

    Student saveStudent(StudentDTO studentDTO);

    Student getStudentByToken(String token);

    Student updateStudent(Long studentId, StudentDTO studentDTO);

    void deleteStudentById(Long studentId);
}