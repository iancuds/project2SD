package com.spring.presentation.dao;

import com.spring.presentation.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeacherDAO extends JpaRepository<Teacher,Long>{

    Teacher findTeacherByEmail(String email);

}
