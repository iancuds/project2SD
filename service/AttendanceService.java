package com.spring.presentation.service;


import com.spring.presentation.model.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> getAllAttendances();

    Attendance getAttendanceById(Long attendanceId);

    Attendance saveAttendance(AttendanceDTO attendanceDTO);

    Attendance updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO);

    void deleteAttendanceById(Long attendanceId);

    List<Attendance> getAllByLaboratoryId(Long labId);
}
