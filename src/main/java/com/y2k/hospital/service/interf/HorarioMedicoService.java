package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.HorarioMedicoDto;
import com.y2k.hospital.dto.Response;

public interface HorarioMedicoService {
    Response createHorarioMedico(HorarioMedicoDto horarioMedicoDto);
    //Response createManyHorarioMedico(HorarioMedicoDto horarioMedicoDto);
    Response getHorarioMedicoById(Long ci_medico, String nombreEspecialidad);
    Response getAllHorarioMedico();
    Response deleteHorarioMedico(Long id, String nombreEspecialidad);
}
