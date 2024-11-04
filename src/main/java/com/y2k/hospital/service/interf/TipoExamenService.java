package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoExamenDto;

public interface TipoExamenService {
    Response createTipoExamen(TipoExamenDto TipoExamenDto);
    Response getTipoExamenById(Long id);
    Response getAllTipoExamen();
    Response updateTipoExamen(Long id, TipoExamenDto TipoExamenDto);
    Response deleteTipoExamen(Long id);
}
