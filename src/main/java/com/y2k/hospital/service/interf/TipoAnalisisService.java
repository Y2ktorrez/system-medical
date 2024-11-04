package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoAnalisisDto;

public interface TipoAnalisisService {
    Response createTipoAnalisis(TipoAnalisisDto tipoAnalisisDto);
    Response getTipoAnalisisById(Long id);
    Response getAllTipoAnalisis();
    Response updateTipoAnalisis(Long id, TipoAnalisisDto tipoAnalisisDto);
    Response deleteTipoAnalisis(Long id);
}
