package com.spring.presentation.service.impl;

import com.spring.presentation.dao.AssignmentDAO;
import com.spring.presentation.dao.StudentDAO;
import com.spring.presentation.dao.SubmissionDAO;
import com.spring.presentation.model.Assignment;
import com.spring.presentation.model.Submission;
import com.spring.presentation.service.SubmissionDTO;
import com.spring.presentation.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubmissionServiceImpl implements SubmissionService {


    private final SubmissionDAO submissionDAO;
    private final StudentDAO studDAO;
    private final AssignmentDAO assDAO;

    public SubmissionServiceImpl(SubmissionDAO submissionDAO, StudentDAO studDAO, AssignmentDAO assDAO) {
        this.submissionDAO = submissionDAO;
        this.studDAO = studDAO;
        this.assDAO = assDAO;
    }

    @Autowired


    public List<Submission> getAllSubmissions(){return submissionDAO.findAll();}

   public  Submission getSubmissionById(Long submissionId){
        return submissionDAO.findOne(submissionId);
    }

    public Submission saveSubmission(SubmissionDTO submissionDTO){
        Submission submissionToBeSaved = new Submission(submissionDTO.getGrade(),  submissionDTO.getDate(), submissionDTO.getLink());
        submissionToBeSaved.setStudent(studDAO.findOne(submissionDTO.getIdstudent()));
       // submissionToBeSaved.getStudent().getSubmissions().add(submissionToBeSaved);
        submissionToBeSaved.setAssignment(assDAO.getOne(submissionDTO.getIdassignment()));
       // submissionToBeSaved.getAssignment().getSubmissions().add(submissionToBeSaved);

        if ((submissionDAO.findSubmissionByAssignmentIdassignmentAndStudentIdstudentAndDate(submissionDTO.getIdassignment(), submissionDTO.getIdstudent(), submissionDTO.getDate())== null)) {
             submissionDAO.save(submissionToBeSaved);
             return submissionToBeSaved;

        } else {
            return null;
        }
    }


    public List<String> getAllGradesForAssignment(Long assignmentId)
    {
        List<String> grades = new ArrayList<String>();

        List<Submission> submissionList = submissionDAO.getAllByAssignmentIdassignment(assignmentId);
        for(Submission s : submissionList)
        {
            String str = "";
            str += studDAO.findOne(s.getStudent().getIdstudent()).getName()+ " "+s.getGrade();
            grades.add(str);
        }
        return grades;
    }

    public Submission updateSubmission(Long submissionId, SubmissionDTO submissionDTO){
        Submission submissionToBeUpdated = submissionDAO.findOne(submissionId);

        if (submissionToBeUpdated != null) {
            submissionToBeUpdated.setStudent(studDAO.findOne(submissionDTO.getIdstudent()));
           // submissionToBeUpdated.getStudent().getSubmissions().add(submissionToBeUpdated);
            submissionToBeUpdated.setDate(submissionDTO.getDate());
            submissionToBeUpdated.setIdsubmission(submissionId);
            submissionToBeUpdated.setAssignment(assDAO.getOne(submissionDTO.getIdassignment()));
           // submissionToBeUpdated.getAssignment().getSubmissions().add(submissionToBeUpdated);
            submissionToBeUpdated.setLink(submissionDTO.getLink());
            submissionToBeUpdated.setGrade(submissionDTO.getGrade());

            submissionDAO.save(submissionToBeUpdated);
            return submissionToBeUpdated;
        } else {
            return null;
        }
    }

    public List<Submission> getAllByStudentIdstudent(Long idstudent)
    {
        return submissionDAO.getAllByStudentIdstudent(idstudent);
    }

   public  void deleteSubmissionById(Long submissionId){

        Submission s = submissionDAO.findOne(submissionId);
     //   s.removeFromAssignment();
     //   s.removeFromStudent();
        submissionDAO.delete(submissionId);}

        public Long getIdBySAndA(String email,String name)
        {

            List<Submission> subs = getAllByStudentIdstudent(studDAO.findStudentByEmail(email).getIdstudent());

            for(Submission sub:subs)
            {
                if(sub.getAssignment().getName().equals(name)) return sub.getIdsubmission();
            }


            return null;
        }

}
