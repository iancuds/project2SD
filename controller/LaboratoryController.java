package com.spring.presentation.controller;


import com.spring.presentation.model.Assignment;
import com.spring.presentation.model.Attendance;
import com.spring.presentation.model.Laboratory;
import com.spring.presentation.service.AssignmentService;
import com.spring.presentation.service.AttendanceService;
import com.spring.presentation.service.LaboratoryDTO;
import com.spring.presentation.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class LaboratoryController {

    private final LaboratoryService labService;
    private final AssignmentService assServ;
    private final AttendanceService attServ;

    @Autowired
    public LaboratoryController(LaboratoryService labService, AssignmentService assServ, AttendanceService attServ) {
        this.labService = labService;
        this.assServ = assServ;
        this.attServ = attServ;
    }








        @GetMapping("getAllLaboratories")
    public List<Laboratory> getAllLaboratries() {
        try {
            return labService.getAllLaboratories();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getFilteredListOfLaboratories")
    public List<Laboratory> getFilteredListOfLaboratories(@RequestParam String word) {
        try {
            return labService.getFilteredLaboratories(word);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getLaboratoryById")
    public Laboratory getLaboratoryById(@RequestParam Long  laboratoryId) {
        try {
            return labService.getLaboratoryById(laboratoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("saveLaboratory")
    public Laboratory saveLaboratory( @RequestParam Long number,
                                      @RequestParam  String date,
                                      @RequestParam  String title,
                                      @RequestParam  String curricula,
                                      @RequestParam  String description) {
        try {

            LaboratoryDTO labDTO = new LaboratoryDTO(number,date,title,curricula,description);
            return labService.saveLaboratory(labDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateLaboratory")
    public Laboratory updateLaboratory(@RequestParam Long labId, @RequestParam Long number,
                                       @RequestParam  String date,
                                       @RequestParam  String title,
                                       @RequestParam  String curricula,
                                       @RequestParam  String description) {
        try {

            LaboratoryDTO labDTO = new LaboratoryDTO(number,date,title,curricula,description);
            return labService.updateLaboratory(labId, labDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getLabIdByNr")
            public Long getLabIdByNr(@RequestParam Long nr, @RequestParam String date)
    {
       return labService.getIdByNr(nr,date);

    }

    @DeleteMapping("deleteLaboratoryById")
    public String deleteLaboratoryById(@RequestParam Long labId) {
        try {
            List<Assignment> asss =assServ.findAllByLaboratoryIdlaboratory(labId);
            List<Attendance> atts =attServ.getAllByLaboratoryId(labId);

            for(Attendance a:atts)
            {
                attServ.deleteAttendanceById(a.getIdattendance());
            }


            for(Assignment a:asss)
            {
                assServ.deleteAssignmentById(a.getIdassignment());
            }

            labService.deleteLaboratoryById(labId);
            return "Laboratory with id = " + labId + " successfully deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }



}
