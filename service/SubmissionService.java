package com.spring.presentation.service;


import com.spring.presentation.model.Submission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubmissionService {

    List<Submission> getAllSubmissions();

    List<Submission> getAllByStudentIdstudent(Long idstudent);

    Submission getSubmissionById(Long submissionId);

    Submission saveSubmission(SubmissionDTO submissionDTO);

    Submission updateSubmission(Long submissionId, SubmissionDTO submissionDTO);

    void deleteSubmissionById(Long submissionId);

    Long getIdBySAndA(String email,String name);

    List<String> getAllGradesForAssignment(Long assignmentId);

}
