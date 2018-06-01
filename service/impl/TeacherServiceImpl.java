package com.spring.presentation.service.impl;

import com.spring.presentation.dao.TeacherDAO;
import com.spring.presentation.model.Student;
import com.spring.presentation.model.Teacher;
import com.spring.presentation.service.TeacherDTO;
import com.spring.presentation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {


    private final TeacherDAO teacherDAO;

    @Autowired
    public TeacherServiceImpl(TeacherDAO tDao)
    {
        this.teacherDAO=tDao;

    }

   public  List<Teacher> getAllTeachers(){

       return teacherDAO.findAll();

    }

    public String logIn(String email, String passwd)
    {

        Teacher teach=teacherDAO.findTeacherByEmail(email);
        if(teach == null) return "The e-mail address is wrong!";
        if(passwd.equals(teach.getToken())) return "You are not yet registered!";
        if(!(teach.getPasswd().equals( passwd))) return "The password or the e-mail address is wrong!";
        return "Successful login!";
    }

    public Teacher getTeacherById(Long teacherId)
    {
        return teacherDAO.findOne(teacherId);
    }

   public  Teacher saveTeacher(TeacherDTO teacherDTO)

    {
        Teacher tToBeSaved = new Teacher(teacherDTO.getEmail(), teacherDTO.getName(), teacherDTO.getToken(), teacherDTO.getToken());

        if ((teacherDAO.findTeacherByEmail(tToBeSaved.getEmail())== null)) {

            teacherDAO.save(tToBeSaved);
            return teacherDAO.findTeacherByEmail(teacherDTO.getEmail());

        } else {
            return null;
        }


    }

    public Teacher getTeacherByToken(String token)
    {
        List<Teacher> list = teacherDAO.findAll();
        for(Teacher t:list)
        {
            if(t.getToken().equals(token)) return t;
        }

        return null;
    }


    public Teacher changePassword(String email, String passwd, String newpasswd)
    {
        Teacher t = teacherDAO.findTeacherByEmail(email);

        if(t.getPasswd().equals(passwd)) t.setPasswd(newpasswd);


        TeacherDTO stddto = new TeacherDTO(t.getEmail(),t.getName(),t.getToken(),  t.getPasswd());
        updateTeacher(t.getIdteacher(), stddto);

        return t;
    }

    public Teacher updateTeacher(Long teacherId, TeacherDTO teacherDTO)
    {
        Teacher teacherToBeUpdated = teacherDAO.findOne(teacherId);

        if (teacherToBeUpdated != null) {
            teacherToBeUpdated.setName(teacherDTO.getName());
            teacherToBeUpdated.setToken(teacherDTO.getToken());
            teacherToBeUpdated.setPasswd(teacherDTO.getPasswd());
            teacherToBeUpdated.setEmail(teacherDTO.getEmail());

            teacherDAO.save(teacherToBeUpdated);
            return teacherToBeUpdated;
        } else {
            return null;
        }
    }

    public void deleteTeacherById(Long teacherId)
    {


            teacherDAO.delete(teacherId);
    }
}
