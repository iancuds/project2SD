package com.spring.presentation.dao;


import com.spring.presentation.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LaboratoryDAO extends JpaRepository<Laboratory,Long > {

    Laboratory findByTitle(String title);

    Laboratory getLaboratoryByNumberAndDate(Long number, String date);

}
