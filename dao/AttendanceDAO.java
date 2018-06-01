package com.spring.presentation.dao;

import com.spring.presentation.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface AttendanceDAO extends JpaRepository<Attendance,Long> {

    public Attendance findAttendanceByStudentIdstudentAndLaboratoryIdlaboratory(Long idstudent, Long idlaboratory);

    List<Attendance> findAllByLaboratoryIdlaboratory(Long idlaboratory);

    public Attendance findAttendanceByStudent_Email(String email);


}
