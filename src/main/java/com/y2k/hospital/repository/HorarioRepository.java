package com.y2k.hospital.repository;

import com.y2k.hospital.Enum.Dia;
import com.y2k.hospital.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    boolean existsByDaysAndHoraInicioAndHoraFin(Dia days, LocalTime horaInicio, LocalTime horaFin);
    boolean existsByDaysAndHoraInicioAndHoraFinAndIdNot(Dia days, LocalTime horaInicio, LocalTime horaFin, Long id);
}
