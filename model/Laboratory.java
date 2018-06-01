package com.spring.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "laboratory")

public class Laboratory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idlaboratory")
    private Long idlaboratory;


    @Column(name = "number")
    private Long number;


    @Column(name = "date")
    private String date;


    @Column(name = "title")
    private String title;


    @Column(name = "curricula")
    private String curricula;


    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy="laboratory")
    private List<Assignment> assignments;

    @JsonIgnore
    @OneToMany(mappedBy="laboratory")
    private List<Attendance>attendances ;

    public Laboratory(Long n, String da, String t, String c, String de)
    {

        this.number=n;
        this.date=da;
        this.title=t;
        this.curricula=c;
        this.description=de;

    }
    public Laboratory(){}

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Long getIdlaboratory() {
        return idlaboratory;
    }

    public void setIdlaboratory(Long idlaboratory) {
        this.idlaboratory = idlaboratory;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "idlaboratory=" + idlaboratory +
                ", number=" + number +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", curricula='" + curricula + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
