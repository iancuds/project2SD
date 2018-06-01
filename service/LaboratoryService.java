package com.spring.presentation.service;

import com.spring.presentation.model.Laboratory;
import com.spring.presentation.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LaboratoryService {

    List<Laboratory> getAllLaboratories();

    List<Laboratory> getFilteredLaboratories(String word);

    Laboratory getLaboratoryById(Long laboratoryId);

    Laboratory saveLaboratory(LaboratoryDTO laboratoryDTO);

    Laboratory updateLaboratory(Long laboratoryId, LaboratoryDTO laboratoryDTO);

    void deleteLaboratoryById(Long laboratoryId);

    Long getIdByNr(Long nr, String date);
}
