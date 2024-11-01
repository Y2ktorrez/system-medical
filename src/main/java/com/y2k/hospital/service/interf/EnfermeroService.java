package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.EnfermeroDto;
import com.y2k.hospital.dto.Response;

public interface EnfermeroService {
    Response registerEnfermero(EnfermeroDto enfermeroDto);
    Response get();
    Response getByCi(Long ci);
    Response update(Long ci, EnfermeroDto enfermeroDto);
    Response delete(Long ci);
}
