package com.spring.presentation.dao;

import com.spring.presentation.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface AssignmentDAO extends JpaRepository<Assignment,Long > {

    Assignment findAssignmentByName(String name);

    Assignment findAssignmentByNameAndAndLaboratory_Number(String name, Long laboratory_Number);

    List<Assignment> findAllByLaboratoryIdlaboratory(Long idlaboratory);


}
