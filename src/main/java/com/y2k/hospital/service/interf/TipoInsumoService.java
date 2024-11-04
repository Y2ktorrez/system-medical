package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoInsumoDto;

public interface TipoInsumoService {
    Response createTipoInsumo(TipoInsumoDto TipoInsumoDto);
    Response getTipoInsumoById(Long id);
    Response getAllTipoInsumo();
    Response updateTipoInsumo(Long id, TipoInsumoDto TipoInsumoDto);
    Response deleteTipoInsumo(Long id);
}
