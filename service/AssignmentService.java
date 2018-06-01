package com.spring.presentation.service;

import com.spring.presentation.model.Assignment;

import java.util.List;

public interface AssignmentService {

    List<Assignment> getAllAssignments();

    List<Assignment> findAllByLaboratoryIdlaboratory(Long idlaboratory);

    Assignment getAssignmentById(Long assignmentId);

    Assignment saveAssignment(AssignmentDTO assignmentDTO);

    Assignment updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO);

    void deleteAssignmentById(Long assignmentId);

    Assignment findAssignmentByNameAndLaboratory_Number(String name, Long laboratory_Number);

    Long getIdByName(String name);
}
