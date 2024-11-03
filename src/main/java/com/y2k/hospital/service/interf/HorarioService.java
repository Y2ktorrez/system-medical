package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.HorarioDto;
import com.y2k.hospital.dto.Response;

public interface HorarioService {
    Response createHorario(HorarioDto horarioDto);
    Response getHorarioById(Long id);
    Response getAllHorarios();
    Response updateHorario(Long id, HorarioDto horarioDto);
    Response deleteHorario(Long id);
}
