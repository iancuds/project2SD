package com.spring.presentation.controller;


import com.spring.presentation.model.Attendance;
import com.spring.presentation.service.AttendanceDTO;
import com.spring.presentation.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("getAllAttendances")
    public List<Attendance> getAllAttendances() {
        try {
            return attendanceService.getAllAttendances();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getAttendanceById")
    public Attendance getAttendanceById(@RequestParam Long attendanceId) {
        try {
            return attendanceService.getAttendanceById(attendanceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getAttendanceByLaboratoryId")
    public List<Attendance> getAttendanceByLaboratoryId(@RequestParam Long labId) {
        try {
            return attendanceService.getAllByLaboratoryId(labId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("saveAttendance")
    public Attendance saveAttendance( @RequestParam Long idstudent,
            @RequestParam Long idlaboratory) {
        try {
            AttendanceDTO attendanceDTO = new AttendanceDTO(idstudent,idlaboratory);
            return attendanceService.saveAttendance(attendanceDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateAttendance")
    public Attendance update(@RequestParam Long attendanceId, @RequestParam Long idstudent,
                             @RequestParam Long idlaboratory) {
        try {
            AttendanceDTO attendanceDTO = new AttendanceDTO(idstudent,idlaboratory);
            return attendanceService.updateAttendance(attendanceId, attendanceDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("deleteAttendanceById")
    public String deleteBookById(@RequestParam Long attendanceId) {
        try {
            attendanceService.deleteAttendanceById(attendanceId);
            return "Attendance with id = " + attendanceId + " successful deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
