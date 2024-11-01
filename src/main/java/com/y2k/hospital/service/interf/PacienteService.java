package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.PacienteDto;
import com.y2k.hospital.dto.Response;

public interface PacienteService {
    Response registerPaciente(PacienteDto pacienteDto);
    Response getAll();
    Response getByCi(Long ci);
}
