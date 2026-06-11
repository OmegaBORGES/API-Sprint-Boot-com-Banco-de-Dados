package com.agenciaviagens.api.repository;

import com.agenciaviagens.api.entity.AtividadeTuristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeTuristicaRepository extends JpaRepository<AtividadeTuristica, Long> {
}
