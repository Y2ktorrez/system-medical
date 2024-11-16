package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Consulta;
import com.y2k.hospital.entity.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    Tratamiento findByConsulta(Consulta consulta);
    List<Tratamiento> findByConsultaId(Long idConsulta);
}
