package com.spring.presentation.service;

import java.io.Serializable;

public class AssignmentDTO implements Serializable {


    private String name;
    private String deadline;
    private String description;
    private Long idlaboratory;

    public AssignmentDTO(String name, Long idlaboratory, String deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.idlaboratory = idlaboratory;
    }

    public AssignmentDTO() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getIdlaboratory() {
        return idlaboratory;
    }

    public void setIdlaboratory(Long idlaboratory) {
        this.idlaboratory = idlaboratory;
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
        return "AssignmentDTO{" +
                "name='" + name + '\'' +
                ", deadline='" + deadline + '\'' +
                ", description='" + description + '\'' +
                ", idlaboratory=" + idlaboratory +
                '}';
    }

}
