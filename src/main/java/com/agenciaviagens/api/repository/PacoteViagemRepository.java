package com.agenciaviagens.api.repository;

import com.agenciaviagens.api.entity.PacoteViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteViagemRepository extends JpaRepository<PacoteViagem, Long> {
}
