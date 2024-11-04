package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.InsumoMedicoDto;
import com.y2k.hospital.dto.Response;

public interface InsumoMedicoService {
    Response createInsumoMedico(InsumoMedicoDto insumoMedicoDto);
    Response getInsumoMedicoById(Long id);
    Response getAllInsumoMedicos();
    Response updateInsumoMedico(Long id, InsumoMedicoDto insumoMedicoDto);
    Response deleteInsumoMedico(Long id);
}
