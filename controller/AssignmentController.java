package com.spring.presentation.controller;

import com.spring.presentation.model.Assignment;
import com.spring.presentation.service.AssignmentDTO;
import com.spring.presentation.service.AssignmentService;
import com.spring.presentation.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final LaboratoryService labService;
@Autowired
    public AssignmentController(AssignmentService assignmentService,  LaboratoryService labService) {
        this.assignmentService = assignmentService;
        this.labService=labService;
    }

    @GetMapping("getAllAssignments")
    public List<Assignment> getAllAssignments() {
        try {
            return assignmentService.getAllAssignments();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getAssignmentsByLab")
    public List<Assignment> findAllByLaboratoryIdlaboratory(@RequestParam Long idlaboratory) {
        try {
            return assignmentService.findAllByLaboratoryIdlaboratory(idlaboratory);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getIdByName")
    public Long getIdByName(@RequestParam  String name)
    {
        return assignmentService.getIdByName(name);
    }

    @GetMapping("getAssignmentById")
    public Assignment getAssignmentById(@RequestParam Long assignmentId) {
        try {
            return assignmentService.getAssignmentById(assignmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("findAssignmentByNameAndLabNr")
    public Assignment findAssignmentByNameAndLabNr(@RequestParam String name, @RequestParam Long nr)
    {
        return assignmentService.findAssignmentByNameAndLaboratory_Number(name, nr);
    }

    @PostMapping("saveAssignment")
    public Assignment saveAssignment(    @RequestParam String name,
                                         @RequestParam String deadline,
                                         @RequestParam String description,
                                         @RequestParam Long idlaboratory) {
        try {
            AssignmentDTO assignmentDTO =new AssignmentDTO(name,idlaboratory,deadline,description);
            assignmentDTO.setIdlaboratory(idlaboratory);
            return assignmentService.saveAssignment(assignmentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateAssignment")
    public Assignment update(@RequestParam Long assignmentId, @RequestParam String name,
                             @RequestParam String deadline,
                             @RequestParam String description,
                             @RequestParam Long idlaboratory) {
        try {
            AssignmentDTO assignmentDTO =new AssignmentDTO(name,idlaboratory,deadline,description);
            return assignmentService.updateAssignment(assignmentId, assignmentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("deleteAssignmentById")
    public String deleteAssignmentById(@RequestParam Long assignmentId) {
        try {
            assignmentService.deleteAssignmentById(assignmentId);
            return "Assignment with id = " + assignmentId + " successful deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
