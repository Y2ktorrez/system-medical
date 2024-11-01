package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.EspecialidadDto;
import com.y2k.hospital.dto.Response;

public interface EspecialidadService {
    Response createEspecialidad(EspecialidadDto especialidadDto);
    Response getEspecialidadById(Long id);
    Response getAllEspecialidades();
    Response updateEspecialidad(Long id, EspecialidadDto especialidadDto);
    Response deleteEspecialidad(Long id);
}
