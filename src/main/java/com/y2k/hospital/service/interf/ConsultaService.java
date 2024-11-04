package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.ConsultaDto;
import com.y2k.hospital.dto.Response;

public interface ConsultaService {
    Response createConsulta(ConsultaDto ConsultaDto);
    Response getConsultaById(Long id);
    Response getAllConsultas();
    Response updateConsulta(Long id, ConsultaDto ConsultaDto);
    Response deleteConsulta(Long id);
}
