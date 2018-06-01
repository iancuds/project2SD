package com.spring.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "idstudent")
        private Long idstudent;

        @Column(name = "name")
        private String name;

        @Column(name = "email")
        private String email;

        @Column(name = "token")
        private String token;

        @Column(name = "groupnr")
        private String groupnr;

        @Column(name = "hobby")
        private String hobby;

        @Column(name = "passwd")
        private String passwd;

    @JsonIgnore
    @OneToMany(mappedBy="student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;

    @JsonIgnore
    @OneToMany(mappedBy="student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions;

        public Student() {
        }
        public Student(String e, String n, String t, String g, String h, String p)
        {

            this.email=e;
            this.name=n;
            this.hobby=h;
            this.passwd=p;
            this.token=t;
            this.groupnr=g;

        }

    public String getGroupnr() {
        return groupnr;
    }

    public void setGroupnr(String groupnr) {
        this.groupnr = groupnr;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public Long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(Long idstudent) {
        this.idstudent = idstudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGroup() {
        return groupnr;
    }

    public void setGroup(String group) {
        this.groupnr = group;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idstudent=" + idstudent +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", group='" + groupnr + '\'' +
                ", hobby='" + hobby + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
