package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Especialidad;
import com.y2k.hospital.entity.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico,Long> {
    List<HorarioMedico> findByMedico_Ci(Long ciMedico);
    List<HorarioMedico> findByMedico_CiAndEspecialidad(Long ci, Especialidad especialidad);
}
