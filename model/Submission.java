package com.spring.presentation.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submission")
public class Submission {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idsubmission")
    private Long idsubmission;

    @Column(name = "grade")
    private Float grade;


    @Column(name = "date")
    private String date;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name="idassignment", nullable=false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name="idstudent", nullable=false)
    private Student student;

    public Submission() {}

    public Submission(Float grade,  String date, String link) {
        this.grade = grade;

        this.date = date;
        this.link = link;
    }

    public Long getIdsubmission() {
        return idsubmission;
    }

    public void setIdsubmission(Long idsubmission) {
        this.idsubmission = idsubmission;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
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

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void removeFromStudent()
    {
        for(Submission s: student.getSubmissions())
        {
            if(s.getIdsubmission().equals(this.idsubmission))
                this.getStudent().getSubmissions().remove(this);

        }

    }

    public void removeFromAssignment()
    {
        for(Submission s: assignment.getSubmissions())
        {
            if(s.getIdsubmission().equals(this.idsubmission))
                this.getAssignment().getSubmissions().remove(this);
        }
    }
}
