package com.horton.test1.repository;

import com.horton.test1.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
