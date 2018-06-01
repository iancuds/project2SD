package com.spring.presentation.dao;


import com.spring.presentation.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SubmissionDAO extends JpaRepository<Submission, Long> {

    Submission findSubmissionByLink(String link);
    Submission findSubmissionByAssignmentIdassignmentAndStudentIdstudentAndDate(Long idassignment, Long idstudent, String date);
    List<Submission> getAllByAssignmentIdassignment(Long idassignment);
    List<Submission> getAllByStudentIdstudent(Long idstudent);
    Submission getSubmissionByStudentEmailAndAssignmentName(String email, String name);

}
