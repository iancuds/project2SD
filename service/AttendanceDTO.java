package com.spring.presentation.service;

import java.io.Serializable;

public class AttendanceDTO implements Serializable {

    private Long idstudent;
    private Long idlaboratory;

    public AttendanceDTO() {

    }

    public AttendanceDTO(Long idstudent, Long idlaboratory) {
        this.idstudent = idstudent;
        this.idlaboratory = idlaboratory;
    }

    public Long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(Long idstudent) {
        this.idstudent = idstudent;
    }

    public Long getIdlaboratory() {
        return idlaboratory;
    }

    public void setIdlaboratory(Long idlaboratory) {
        this.idlaboratory = idlaboratory;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "idstudent=" + idstudent +
                ", idlaboratory=" + idlaboratory +
                '}';
    }
}
