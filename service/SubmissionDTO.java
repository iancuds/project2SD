package com.spring.presentation.service;

import java.io.Serializable;

public class SubmissionDTO implements Serializable {



        private Float grade;
        private Long idassignment;
        private Long idstudent;
        private String date;
        private String link;

    public SubmissionDTO() {
    }

    public SubmissionDTO(Float grade, Long idassignment, Long idstudent, String date, String link) {
            this.grade = grade;
            this.idassignment = idassignment;
            this.idstudent = idstudent;
            this.date = date;
            this.link = link;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Long getIdassignment() {
        return idassignment;
    }

    public void setIdassignment(Long idassignment) {
        this.idassignment = idassignment;
    }

    public Long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(Long idstudent) {
        this.idstudent = idstudent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "grade=" + grade +
                ", idassignment=" + idassignment +
                ", idstudent=" + idstudent +
                ", date='" + date + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
