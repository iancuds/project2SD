package com.spring.presentation.controller;


import com.spring.presentation.model.Teacher;
import com.spring.presentation.service.StudentService;
import com.spring.presentation.service.TeacherDTO;
import com.spring.presentation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("getAllTeachers")
    public List<Teacher> getAllTeachers() {
        try {
            return teacherService.getAllTeachers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @GetMapping("getTeacherById")
    public Teacher getTeacherById(@RequestParam Long teacherId) {
        try {
            return teacherService.getTeacherById(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("register")
    public Teacher register(@RequestParam String token, @RequestParam String passwd)
    {
        Teacher std=null;
        try {

            std = teacherService.getTeacherByToken(token);

            std.setPasswd(passwd);

            TeacherDTO stddto=new TeacherDTO();
            Long id=std.getIdteacher();

            mapTeacher(stddto, std, id, true);

            teacherService.updateTeacher(id, stddto);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return std;
    }


    @PostMapping("saveTeacher")
    public Teacher saveTeacher(@RequestParam String email, @RequestParam String name) {
        try {

            String token = getSaltString();
            TeacherDTO teacherDTO = new TeacherDTO(email,name,token,"string");

            teacherDTO.setToken(token);
            return teacherService.saveTeacher(teacherDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PutMapping("updateTeacher")
    public Teacher updateTeacher(@RequestParam Long teacherId,  @RequestParam String email,  @RequestParam String name) {
        try {

            Teacher tch = teacherService.getTeacherById(teacherId);
            TeacherDTO tchdto = new TeacherDTO(email,name,tch.getToken(),tch.getPasswd());

            return teacherService.updateTeacher(teacherId, tchdto);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("deleteTeacherById")
    public String deleteTeacherById(@RequestParam Long teacherId) {
        try {
            teacherService.deleteTeacherById(teacherId);
            return "Teacher with id = " + teacherId + " successfully deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("logIn")
    public String logIn(@RequestParam String email, @RequestParam String passwd) {
        try {
            return teacherService.logIn(email, passwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("changePassword")
    public Teacher changePassword(@RequestParam String email, @RequestParam String passwd, @RequestParam  String newpasswd) {
        try {
            return teacherService.changePassword(email,passwd, newpasswd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void mapTeacher(TeacherDTO stddto, Teacher std, Long id, boolean way)
    {
        if(way == false)
        {
            std.setToken(stddto.getToken());
            std.setPasswd(stddto.getPasswd());
            std.setEmail(stddto.getEmail());
            std.setName(stddto.getName());

        }
        else
        {
            stddto.setToken(std.getToken());
            stddto.setPasswd(std.getPasswd());
            stddto.setEmail(std.getEmail());
            stddto.setName(std.getName());



        }
        id=std.getIdteacher();
    }

    protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()~{}|:<>?/.,';][`";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 128) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}