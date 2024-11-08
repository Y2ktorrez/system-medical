package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findAllByConsultaId(Long consultaId);
}
