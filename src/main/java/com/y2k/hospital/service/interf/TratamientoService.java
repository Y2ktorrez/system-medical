package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.TratamientoDto;
import com.y2k.hospital.dto.Response;

public interface TratamientoService {
    Response createTratamiento(TratamientoDto TratamientoDto);
    Response getTratamientoById(Long id);
    Response getAllTratamientos();
}
