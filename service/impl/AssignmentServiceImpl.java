package com.spring.presentation.service.impl;

import com.spring.presentation.dao.AssignmentDAO;

import com.spring.presentation.dao.LaboratoryDAO;
import com.spring.presentation.model.Assignment;
import com.spring.presentation.model.Laboratory;
import com.spring.presentation.service.AssignmentDTO;
import com.spring.presentation.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AssignmentServiceImpl implements AssignmentService{



    private final AssignmentDAO assignmentDAO ;
    private final LaboratoryDAO labDAO ;


    public AssignmentServiceImpl(AssignmentDAO assignmentDAO, LaboratoryDAO labDAO) {
        this.assignmentDAO = assignmentDAO;
        this.labDAO = labDAO;
    }

    @Autowired


    @Override
    public List<Assignment> getAllAssignments() {

      return  assignmentDAO.findAll();
    }

    @Override
    public Assignment getAssignmentById(Long assignmentId) {

        return assignmentDAO.findOne(assignmentId);
    }

    @Override
    public Assignment saveAssignment(AssignmentDTO assignmentDTO) {

        Assignment astobesaved = new Assignment(assignmentDTO.getName(),  assignmentDTO.getDeadline(), assignmentDTO.getDescription());

        if(assignmentDAO.findAssignmentByName(assignmentDTO.getName())== null)
        {
            astobesaved.setName(assignmentDTO.getName());
            astobesaved.setDeadline(assignmentDTO.getDeadline());
            astobesaved.setDescription(assignmentDTO.getDescription());
            astobesaved.setLaboratory(labDAO.findOne(assignmentDTO.getIdlaboratory()));

            assignmentDAO.save(astobesaved);

            return astobesaved;
        }
        return null;
    }

    @Override
    public Assignment updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO) {
        Assignment assign = assignmentDAO.findOne(assignmentId);
        if(assign != null)
        {
            assign.setDescription(assignmentDTO.getDescription());
            assign.setDeadline(assignmentDTO.getDeadline());
            assign.setName(assignmentDTO.getName());
            assign.setLaboratory(labDAO.findOne(assignmentDTO.getIdlaboratory()));
            assignmentDAO.save(assign);
            return assign;
        }
        return null;
    }

    @Override
    public void deleteAssignmentById(Long assignmentId) {

        Assignment a=assignmentDAO.findOne(assignmentId);
      //  a.removeFromLaboratory();
        assignmentDAO.delete(assignmentId);
    }

    public List<Assignment> findAllByLaboratoryIdlaboratory(Long idlaboratory)
    {
        return assignmentDAO.findAllByLaboratoryIdlaboratory(idlaboratory);
    }
    public Assignment findAssignmentByNameAndLaboratory_Number(String name, Long laboratory_Number)
    {
        Assignment a = assignmentDAO.findAssignmentByNameAndAndLaboratory_Number(name, laboratory_Number);
        return a;
    }

    public Long getIdByName(String name)
    {
        List<Assignment> list = getAllAssignments();

        for(Assignment a:list)
        {
            if(a.getName().equals(name)) return a.getIdassignment();
        }
        return null;
    }
}
