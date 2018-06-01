package com.spring.presentation.service.impl;

import com.spring.presentation.dao.LaboratoryDAO;
import com.spring.presentation.model.Laboratory;
import com.spring.presentation.service.LaboratoryDTO;
import com.spring.presentation.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    private final LaboratoryDAO laboratoryDAO ;

@Autowired
public LaboratoryServiceImpl(LaboratoryDAO laboratoryDAO )
{
    this.laboratoryDAO = laboratoryDAO;
}

    public List<Laboratory> getAllLaboratories()
    {
        return laboratoryDAO.findAll();
    }

    public Laboratory getLaboratoryById(Long laboratoryId)
    {
        return laboratoryDAO.findOne(laboratoryId);
    }

    public Laboratory saveLaboratory(LaboratoryDTO laboratoryDTO)
    {

        Laboratory laboratoryToBeSaved = new Laboratory(laboratoryDTO.getNumber(), laboratoryDTO.getDate(), laboratoryDTO.getTitle(), laboratoryDTO.getCurricula(), laboratoryDTO.getDescription());

        if ((laboratoryDAO.findByTitle(laboratoryToBeSaved.getTitle() )== null)) {

            laboratoryDAO.save(laboratoryToBeSaved);
            return laboratoryDAO.findByTitle(laboratoryDTO.getTitle());

        } else {
            return null;
        }

    }

    public List<Laboratory> getFilteredLaboratories(String word)
    {
        List<Laboratory> outp =  new ArrayList<Laboratory>();
        List<Laboratory> inp = laboratoryDAO.findAll();
        for(Laboratory l :inp)
        {
            String curricula = l.getCurricula();
            String description = l.getDescription();

            boolean found1, found2;

            found1 = curricula.contains(word);
            found2 = description.contains(word);

            if((found1 == true) || (found2 == true)) outp.add(l);

        }

        return outp;
    }

   public Laboratory updateLaboratory(Long laboratoryId, LaboratoryDTO laboratoryDTO) {
       Laboratory labToBeUpdated = laboratoryDAO.findOne(laboratoryId);
       if (labToBeUpdated != null)
       {
           labToBeUpdated.setCurricula(laboratoryDTO.getCurricula());
           labToBeUpdated.setDate(laboratoryDTO.getDate());
           labToBeUpdated.setDescription(laboratoryDTO.getDescription());
           labToBeUpdated.setTitle(laboratoryDTO.getTitle());
           labToBeUpdated.setNumber(laboratoryDTO.getNumber());

           laboratoryDAO.save(labToBeUpdated);

           return labToBeUpdated;
       }
else return null;
    }

   public void deleteLaboratoryById(Long laboratoryId)
   {
       laboratoryDAO.delete(laboratoryId);
    }

    public Long getIdByNr(Long nr, String date)
    {
        Laboratory lab = laboratoryDAO.getLaboratoryByNumberAndDate(nr, date);
        return lab.getIdlaboratory();
    }

}
