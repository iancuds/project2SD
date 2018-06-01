package com.spring.presentation.service;

import com.spring.presentation.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {


        List<Teacher> getAllTeachers();

        Teacher getTeacherById(Long teacherId);

        Teacher saveTeacher(TeacherDTO teacherDTO);

        String logIn(String email, String passwd);

        Teacher changePassword(String email, String passwd, String newpasswd);

        Teacher getTeacherByToken(String token);

        Teacher updateTeacher(Long teacherId, TeacherDTO teacherDTO);

        void deleteTeacherById(Long teacherId);
    }

