package com.spring.presentation.model;

import javax.persistence.*;


@Entity
@Table(name="attendance")
public class Attendance {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idattendance")
    private Long idattendance;

 /*   @Column(name = "idstudent")
    private Long idstudent;

   @Column(name = "idlaboratory")
   private Long idlaboratory;
*/

    @ManyToOne
    @JoinColumn(name="idlaboratory", nullable=false)
    private Laboratory laboratory;

    @ManyToOne
    @JoinColumn(name="idstudent", nullable=false)
    private Student student;

   // public Attendance() {
      //  this.idstudent = idstudent;

     //   this.idlaboratory = idlaboratory;
   // }

    public Attendance(){}
    public Long getIdattendance() {
        return idattendance;
    }

    public void setIdattendance(Long idattendance) {
        this.idattendance = idattendance;
    }

 /*   public Long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(Long idstudent) {
        this.idstudent = idstudent;
    }
*/
 //   public Long getIdlaboratory() {
       // return idlaboratory;
  //  }

 /*   public void setIdlaboratory(Long idlaboratory) {
        this.idlaboratory = idlaboratory;
    }
*/

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "idattendance=" + idattendance +
                ", idstudent='" + student.getIdstudent() + '\'' +
               ", idlaboratory=" + laboratory.getIdlaboratory() +
                '}';
    }


    public void removeFromStudent()
    {
        for(Attendance a:student.getAttendances())
        {
            if(a.getIdattendance().equals(this.idattendance))
              this.getStudent().getAttendances().remove(this);
        }
    }

    public void removeFromLaboratory()
    {
        for(Attendance a: laboratory.getAttendances())
        {
            if(a.getIdattendance().equals(this.idattendance))
                this.getLaboratory().getAttendances().remove(this);
        }
    }
}
