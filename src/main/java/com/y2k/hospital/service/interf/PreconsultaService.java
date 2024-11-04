package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.PreconsultaDto;
import com.y2k.hospital.dto.Response;

public interface PreconsultaService {
    Response createPreconsulta(PreconsultaDto preconsultaDto);
    Response getPreconsultaById(Long id);
    Response getAllPreconsultas();
    Response updatePreconsulta(Long id, PreconsultaDto preconsultaDto);
    Response deletePreconsulta(Long id);
}
