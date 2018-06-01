package com.spring.presentation.controller;


import com.spring.presentation.model.Assignment;
import com.spring.presentation.model.Submission;
import com.spring.presentation.service.AssignmentService;
import com.spring.presentation.service.StudentService;
import com.spring.presentation.service.SubmissionDTO;
import com.spring.presentation.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("submission")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final StudentService stdService;
    private final AssignmentService assService;

    @Autowired
    public SubmissionController(SubmissionService submissionService, StudentService stdService, AssignmentService assService) {
        this.submissionService = submissionService;
        this.stdService = stdService;
        this.assService = assService;
    }





    @GetMapping("getAllSubmissions")
    public List<Submission> getAllSubmissions() {
        try {
            return submissionService.getAllSubmissions();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("getAllSubmissionsByStudent")
    public List<Submission> getAllSubmissionsByStudent(@RequestParam Long idstudent) {
        try {
            return submissionService.getAllByStudentIdstudent(idstudent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @GetMapping("getSubmissionById")
    public Submission getSubmissionById(@RequestParam Long submissionId) {
        try {
            return submissionService.getSubmissionById(submissionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @GetMapping("getAllGradesForAssignment")
    public List<String> getAllGradesForAssignment(@RequestParam Long assignmentId) {
        try {
            return submissionService.getAllGradesForAssignment(assignmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("saveSubmission")
    public Submission saveSubmission(  @RequestParam Float grade,@RequestParam Long idassignment, @RequestParam Long idstudent, @RequestParam String date, @RequestParam String link) {
        try {

            SubmissionDTO submissionDTO = new SubmissionDTO(grade,idassignment,idstudent,date,link);
            return submissionService.saveSubmission(submissionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateSubmission")
    public Submission updateSubmission(@RequestParam Long submissionId, @RequestParam Float grade,@RequestParam Long idassignment, @RequestParam Long idstudent, @RequestParam String date, @RequestParam String link) {
        try {

            SubmissionDTO submissionDTO = new SubmissionDTO(grade,idassignment,idstudent,date,link);

            return submissionService.updateSubmission(submissionId, submissionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("gradeSubmission")
    public Submission gradeSubmission(@RequestParam Long submissionId, @RequestParam Float grade) {
        try {
            Submission subToGrade = submissionService.getSubmissionById(submissionId);
            System.out.print(subToGrade.getGrade());
            System.out.print(grade);
            subToGrade.setGrade(grade);
            SubmissionDTO subdto =new SubmissionDTO();
            mapSubmission(subdto,subToGrade,submissionId, true);
            submissionService.updateSubmission(submissionId,subdto);
            return subToGrade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @DeleteMapping("deleteSubmissionById")
    public String deleteSubmissionById(@RequestParam Long submissionId) {
        try {
            submissionService.deleteSubmissionById(submissionId);
            return "Submission with id = " + submissionId + " successfully deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("getIdByStudentAndAssignment")
    public Long getIdBySAndA(@RequestParam String email, @RequestParam String name)
    {
        return submissionService.getIdBySAndA(email,name);
    }

    public void mapSubmission(SubmissionDTO subdto, Submission sub, Long id, boolean way)
    {
        if(way == false)
        {
            sub.setGrade(subdto.getGrade());
            sub.setLink(subdto.getLink());
            sub.setStudent(stdService.getStudentById(subdto.getIdstudent()));
            sub.setDate(subdto.getDate());
            sub.setAssignment(assService.getAssignmentById(subdto.getIdassignment()));
        }
        else
        {
            subdto.setGrade(sub.getGrade());
            subdto.setLink(sub.getLink());
            subdto.setIdstudent(sub.getStudent().getIdstudent());
            subdto.setDate(sub.getDate());
            subdto.setIdassignment(sub.getAssignment().getIdassignment());
        }
        id=sub.getIdsubmission();
    }
  
}
