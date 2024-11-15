package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import com.y2k.hospital.entity.Paciente;

import java.util.List;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
    List<Ficha> findByPaciente(Paciente paciente);
}
