package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalisisRepository extends JpaRepository<Analisis, Long> {
    List<Analisis> findAllByConsultaId(Long consultaId);
}
