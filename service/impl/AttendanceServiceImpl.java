package com.spring.presentation.service.impl;

import com.spring.presentation.dao.AttendanceDAO;
import com.spring.presentation.dao.LaboratoryDAO;
import com.spring.presentation.dao.StudentDAO;
import com.spring.presentation.model.Attendance;
import com.spring.presentation.model.Student;
import com.spring.presentation.service.AttendanceDTO;
import com.spring.presentation.service.AttendanceService;
import com.spring.presentation.service.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDAO attendanceDAO ;
    private final LaboratoryDAO labDao;
    private final StudentDAO stdDao;



    public AttendanceServiceImpl(AttendanceDAO attendanceDAO, LaboratoryDAO labDao, StudentDAO stdDao) {
        this.labDao=labDao;
        this.attendanceDAO = attendanceDAO;
        this.stdDao =stdDao;
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceDAO.findAll();
    }

    @Override
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceDAO.findOne(attendanceId);
    }

    @Override
    public Attendance saveAttendance(AttendanceDTO attendanceDTO) {

       Attendance attendanceToBeSaved = new Attendance();
       attendanceToBeSaved.setStudent(stdDao.findOne(attendanceDTO.getIdstudent()));

       attendanceToBeSaved.setLaboratory((labDao.findOne(attendanceDTO.getIdlaboratory())));

       if(attendanceDAO.findAttendanceByStudentIdstudentAndLaboratoryIdlaboratory(attendanceDTO.getIdstudent(),attendanceDTO.getIdlaboratory()) == null)
       {
           attendanceDAO.save(attendanceToBeSaved);
           return attendanceToBeSaved;
       }
       return null;

    }

    @Override
    public List<Attendance> getAllByLaboratoryId(Long labId) {
        return attendanceDAO.findAllByLaboratoryIdlaboratory(labId);
    }

    @Override
    public Attendance updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO) {
        Attendance attobesaved=attendanceDAO.findOne(attendanceId);
        if(attobesaved != null)
        {
            attobesaved.setLaboratory(labDao.getOne(attendanceDTO.getIdlaboratory()));

            attobesaved.setStudent((stdDao.getOne(attendanceDTO.getIdstudent())));

       /*     attobesaved.getStudent().getAttendances().add(attobesaved);
            attobesaved.getLaboratory().getAttendances().add(attobesaved);
*/
            attendanceDAO.save(attobesaved);

            return attobesaved;
        }
        return null;
    }

    @Override
    public void deleteAttendanceById(Long attendanceId) {


        Attendance a = attendanceDAO.findOne(attendanceId);
    //    a.removeFromLaboratory();
    //    a.removeFromStudent();
        attendanceDAO.delete(attendanceId);

    }


}
