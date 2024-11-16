package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Ficha;
import com.y2k.hospital.entity.Preconsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreconsultaRepository extends JpaRepository<Preconsulta, Long> {
    List<Preconsulta> findByFichaIn(List<Ficha> fichas);
}
