package com.horton.test1.repository;

import com.horton.test1.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    Optional<Participante> findByNombre(String nombre);
}
