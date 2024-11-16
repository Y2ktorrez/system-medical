package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Consulta;
import com.y2k.hospital.entity.Preconsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPreconsulta(Preconsulta preconsulta);
}
