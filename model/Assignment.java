package com.spring.presentation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="assignment")
public class Assignment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idassignment")
    private Long idassignment;

    @Column(name = "name")
    private String name;



    @Column(name="deadline")
    private String deadline;



    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="idlaboratory", nullable=false)
    private Laboratory laboratory;


    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy="assignment")
    private List<Submission> submissions;


    public Assignment(String name,  String deadline, String description) {
        this.name = name;

        this.deadline = deadline;
        this.description = description;
    }

    public Assignment()
    {}

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdassignment() {
        return idassignment;
    }

    public void setIdassignment(Long idassignment) {
        this.idassignment = idassignment;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "idassignment=" + idassignment +
                ", name='" + name + '\'' +
                ", idlaboratory=" + laboratory.getIdlaboratory() +
                ", deadline='" + deadline + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return (getIdassignment().equals( that.getIdassignment())) &&
               (getName().equals( that.getName()) )&&
                (getDeadline().equals( that.getDeadline()) )&&
                (getDescription().equals(that.getDescription()) )&&
                (getLaboratory().equals( that.getLaboratory()) )&&
                (getSubmissions().equals( that.getSubmissions()));
    }

    @Override
    public int hashCode() {


        return Objects.hash(getIdassignment().hashCode(), getName().hashCode(), getDeadline().hashCode(), getDescription().hashCode(), (getLaboratory()).hashCode(), getSubmissions().hashCode());
    }
    */

  public void removeFromLaboratory()
  {
      for(Assignment a:laboratory.getAssignments())
      {
          if(a.getIdassignment().equals(this.idassignment))
              this.getLaboratory().getAssignments().remove(this);
      }
  }
}
