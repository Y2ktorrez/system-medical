package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.FichaDto;
import com.y2k.hospital.dto.Response;

public interface FichaService {
    Response createFicha(FichaDto fichaDto);
    Response getFichaById(Long id);
    Response getAllFichas();
    Response updateFicha(Long id, FichaDto fichaDto);
    Response deleteFicha(Long id);
    Response getFichasByPacienteId(Long id);
}
