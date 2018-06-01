package com.spring.presentation.controller;


import com.spring.presentation.model.Student;
import com.spring.presentation.model.Submission;
import com.spring.presentation.service.StudentDTO;
import com.spring.presentation.service.StudentService;
import com.spring.presentation.service.SubmissionService;
import com.spring.presentation.service.impl.SubmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final SubmissionService subServ;


    @Autowired
    public StudentController(StudentService studentService, SubmissionService subServ) {
        this.studentService = studentService;
        this.subServ = subServ;
    }




    @GetMapping("getAllStudents")
    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    @GetMapping("logIn")
    public String logIn(@RequestParam String email, @RequestParam String passwd) {
        try {
            return studentService.logIn(email, passwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getIdByEmail")
    public Long getIdByEmail(String email)
    {
        Student s = findStudentByEmail(email);
        return s.getIdstudent();
    }

    @GetMapping("findStudentByEmail")
    public Student findStudentByEmail(@RequestParam String email)
    {
        Student student = studentService.findStudentByEmail(email);
        return student;
    }

    @GetMapping("getStudentById")
    public Student getStudentById(@RequestParam Long studentId) {
        try {
            return studentService.getStudentById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("register")
    public Student register(@RequestParam String token, @RequestParam String passwd)
    {
        Student std=null;
        try {

             std = studentService.getStudentByToken(token);
            System.out.println("studentul gasit: "+std);
            std.setPasswd(passwd);

            StudentDTO stddto=new StudentDTO();
           Long id=std.getIdstudent();

           mapStudent(stddto, std, id, true);

           studentService.updateStudent(id, stddto);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
return std;
    }

    @PostMapping("saveStudent")
    public Student saveStudent(@RequestParam String email, @RequestParam String group, @RequestParam String hobby, @RequestParam String name) {
        try {

            String token=getSaltString();
            StudentDTO studentDTO= new StudentDTO(email,name,token,group,hobby,"string");

            return studentService.saveStudent(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateStudent")
    public Student updateStudent(@RequestParam Long studentId, @RequestParam String email, @RequestParam String group, @RequestParam String hobby, @RequestParam String name) {
        try {
            Student std = studentService.getStudentById(studentId);
            StudentDTO stddto = new StudentDTO(email,name,std.getToken(),group,hobby,std.getPasswd());
           //mapStudent(stddto,std,studentId,true);
            return studentService.updateStudent(studentId, stddto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("changePassword")
    public Student changePassword(@RequestParam String email, @RequestParam String passwd, @RequestParam  String newpasswd) {
        try {
            return studentService.changePassword(email,passwd, newpasswd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("findIdByEmail")
    public Long findIdByEmail(@RequestParam String email)
    {
        Student s=studentService.findStudentByEmail(email);
        return s.getIdstudent();
    }

    @DeleteMapping("deleteStudentById")
    public String deleteStudentById(@RequestParam Long studentId) {
        try {



            List<Submission> subs = subServ.getAllByStudentIdstudent(studentId);

            for(Submission s : subs)
            {
                subServ.deleteSubmissionById(s.getIdsubmission());
            }

            studentService.deleteStudentById(studentId);
            return "Student with id = " + studentId + " successfully deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public void mapStudent(StudentDTO stddto, Student std, Long id, boolean way)
    {
        if(way == false)
        {
            std.setToken(stddto.getToken());
            std.setPasswd(stddto.getPasswd());
            std.setEmail(stddto.getEmail());
            std.setGroup(stddto.getGroup());
            std.setHobby(stddto.getHobby());
            std.setName(stddto.getName());

        }
        else
        {
            stddto.setToken(std.getToken());
            stddto.setPasswd(std.getPasswd());
            stddto.setEmail(std.getEmail());
            stddto.setGroup(std.getGroup());
            stddto.setHobby(std.getHobby());
            stddto.setName(std.getName());



        }
        id=std.getIdstudent();
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
