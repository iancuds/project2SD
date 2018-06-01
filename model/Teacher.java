package com.spring.presentation.model;

import javax.persistence.*;


@Entity
@Table(name = "teacher")
public class Teacher {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idteacher")
    private Long idteacher;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "passwd")
    private String passwd;

    public Teacher() {
    }
    public Teacher(String e, String n, String t, String p)
    {

        this.email=e;
        this.name=n;
        this.passwd=p;
        this.token=t;


    }

    public Long getIdteacher() {
        return idteacher;
    }

    public void setIdstudent(Long idteacher) {
        this.idteacher= idteacher;
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


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idteacher=" + idteacher +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
