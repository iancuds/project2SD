package com.spring.presentation.service.impl;

import com.spring.presentation.dao.StudentDAO;
import com.spring.presentation.model.Student;
import com.spring.presentation.service.StudentDTO;
import com.spring.presentation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentDAO.findOne(studentId);
    }


    public Student getStudentByToken(String token)
    {
        List<Student> list = studentDAO.findAll();
        for(Student std:list)
        {
            if(std.getToken().equals(token)) return std;
        }

        return null;
    }

    public Student saveStudent(StudentDTO studentDTO) {
        Student studentToBeSaved = new Student(studentDTO.getEmail(), studentDTO.getName(), studentDTO.getToken(), studentDTO.getGroup(), studentDTO.getHobby(), studentDTO.getToken());

        if ((studentDAO.findStudentByEmail(studentToBeSaved.getEmail() )== null)) {

            studentDAO.save(studentToBeSaved);
            return studentDAO.findStudentByEmail(studentDTO.getEmail());

        } else {
            return null;
        }
    }

    public Student findStudentByEmail(String email)
    {
        Student student = new Student();
        student = studentDAO.findStudentByEmail(email);
        return student;

    }

    public String logIn(String email, String passwd)
    {

                
        Student std=studentDAO.findStudentByEmail(email);
        if(std == null) return "The e-mail address is wrong!";
        if(passwd.equals(std.getToken())) return "You are not yet registered!";
        if(!(std.getPasswd().equals(passwd))) return "The password or the e-mail address is wrong!";
        return "Successful login!";
    }

    public Student updateStudent(Long studentId, StudentDTO studentDTO) {
        Student studentToBeUpdated = studentDAO.findOne(studentId);

        if (studentToBeUpdated != null) {
            studentToBeUpdated.setName(studentDTO.getName());
            studentToBeUpdated.setToken(studentDTO.getToken());
            studentToBeUpdated.setPasswd(studentDTO.getPasswd());
            studentToBeUpdated.setHobby(studentDTO.getHobby());
            studentToBeUpdated.setGroup(studentDTO.getGroup());
            studentToBeUpdated.setEmail(studentDTO.getEmail());

            studentDAO.save(studentToBeUpdated);
            return studentToBeUpdated;
        } else {
            return null;
        }
    }

    public Student changePassword(String email, String passwd, String newpasswd)
    {
        Student std = studentDAO.findStudentByEmail(email);


        if(std.getPasswd().equals(passwd)) std.setPasswd(newpasswd);


        StudentDTO stddto = new StudentDTO(std.getEmail(),std.getName(),std.getToken(), std.getGroupnr(), std.getHobby(), std.getPasswd());
        updateStudent(std.getIdstudent(), stddto);

        return std;
    }

    public void deleteStudentById(Long studentId) {
        studentDAO.delete(studentId);
    }

}
