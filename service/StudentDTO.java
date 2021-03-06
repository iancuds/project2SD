package com.spring.presentation.service;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private String email;
    private String name;
    private String token;
    private String groupnr;
    private String hobby;
    private String passwd;

    public StudentDTO()
    {}
    public StudentDTO(String e, String n, String t, String g, String h, String p)
    {
        this.email=e;
        this.name=n;
        this.hobby=h;
        this.passwd=p;
        this.token=t;
        this.groupnr=g;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "StudentDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", group='" + groupnr + '\'' +
                ", hobby='" + hobby + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}


